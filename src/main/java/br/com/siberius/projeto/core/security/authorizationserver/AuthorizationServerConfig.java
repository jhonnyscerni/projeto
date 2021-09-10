package br.com.siberius.projeto.core.security.authorizationserver;

import br.com.siberius.projeto.core.security.authorizationserver.customclaims.JwtCustomClaimsTokenEnhancer;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    /**
     * Somente para o fluxo de password
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Configuração da Chave .jks
     */
    @Autowired
    private JwtKeyStoreProperties jwtKeyStoreProperties;

    @Autowired
    private DataSource dataSource;

    /**
     * Configurar os clientes que podem acessar esse Authorization Server e que depois eles vão acessar os recursos protegidos do Resourse Server
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
//            .inMemory()
//            .withClient("projeto-web")
//            .secret(passwordEncoder.encode("web123"))
//            .authorizedGrantTypes("password", "refresh_token")
//            .scopes("WRITE", "READ")
//            .accessTokenValiditySeconds(60 * 60 * 6) // 6 horas (padrão é 12 horas)

            // type Legado, nao aconselhado usar pois passa o token pela url
            //http://localhost:8080/projeto-api/oauth/authorize?response_type=token&client_id=foodnanalytics&state=abc&redirect_uri=http://aplicacao-cliente
//            .and()
//            .withClient("webadmin")
//            .authorizedGrantTypes("implicit")
//            .scopes("WRITE", "READ")
//            .redirectUris("http://aplicacao-cliente")

            // authorization_code
            //http://localhost:8080/oauth/authorize?response_type=code&client_id=projetoanalytics&state=abc&redirect_uri=http://www.projetoanalytics.local:8082
//            .and()
//            .withClient("foodnanalytics")
//            .secret(passwordEncoder.encode("food123"))
//            .authorizedGrantTypes("authorization_code")
//            .scopes("WRITE", "READ")
//            .redirectUris("http://aplicacao-cliente")

            // client_credentials usado mais para processamento de alguma coisa dentro da Api sem interaçao com usuario
//            .and()
//            .withClient("faturamento")
//            .secret(passwordEncoder.encode("faturamento123"))
//            .authorizedGrantTypes("client_credentials")
//            .scopes("WRITE", "READ")

            // usado para fazer a comunicação com o Resouce Serve URI de introspecção
//            .and()
//            .withClient("checktoken")
//            .secret(passwordEncoder.encode("check123"));
    }

    /**
     * para permitir que seja usado o "check_token" http://localhost:8080/projeto-api/oauth/check_token
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.checkTokenAccess("isAuthenticated()");
        security.checkTokenAccess("permitAll()")
            // End point que gera a chave pública no formato PEM ----> GET ".../oauth/token_key"
            .tokenKeyAccess("permitAll()")
            // ao inves de passar como autenticate Basic [usuario senha], permitir passar no Body client_id podendo deixar a senha em branco
            .allowFormAuthenticationForClients();
    }

    /**
     * Somente para o fluxo de password
     *     @Override
     *     public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
     *         endpoints.authenticationManager(authenticationManager);
     *     }
     */

    /**
     * Quando usa refresh_token e password juntos precisa do userDetails
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // Implementando CustomClaims
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(
            Arrays.asList(new JwtCustomClaimsTokenEnhancer(), jwtAccessTokenConverter()));
        
        endpoints
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
            // Implementando JWT
            .accessTokenConverter(jwtAccessTokenConverter())
            // Implementando CustomClaims
            .tokenEnhancer(enhancerChain)
            // Caso queira que apareça o escopo da aprovação de READ, WHITE e tem que ser depois do accessTokenConverter
            .approvalStore(approvalStore(endpoints.getTokenStore()))
            // Implementando o suporte a PKCE com o fluxo Authorization Code
            .tokenGranter(tokenGranter(endpoints));
    }

    // Criado para caso queira que aparece o escopo da aprovação de READ, WHITE
    private ApprovalStore approvalStore(TokenStore tokenStore) {
        TokenApprovalStore approvalStore = new TokenApprovalStore();
        approvalStore.setTokenStore(tokenStore);

        return approvalStore;
    }


    /**
     * Implementando o suporte a PKCE com o fluxo Authorization Code
     */
    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        PkceAuthorizationCodeTokenGranter pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
            endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(),
            endpoints.getOAuth2RequestFactory());

        List<TokenGranter> granters = Arrays.asList(
            pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());

        return new CompositeTokenGranter(granters);
    }

    /**
     *  Implementando JWT
     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        jwtAccessTokenConverter.setSigningKey("89a7sd89f7as98f7dsa98fds7fd89sasd9898asdf98s");
//
//        return jwtAccessTokenConverter;
//    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

        ClassPathResource jksResource = new ClassPathResource(jwtKeyStoreProperties.getPath());
        String keyStorePass = jwtKeyStoreProperties.getPassword();
        String keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(jksResource, keyStorePass.toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyPairAlias);

        jwtAccessTokenConverter.setKeyPair(keyPair);

        return jwtAccessTokenConverter;
    }

}
