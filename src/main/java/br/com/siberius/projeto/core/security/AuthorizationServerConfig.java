package br.com.siberius.projeto.core.security;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Somente para o fluxo de password
     */
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Configurar os clientes que podem acessar esse Authorization Server e que depois eles vão acessar os recursos protegidos do Resourse Server
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()
            .withClient("algafood-web")
            .secret(passwordEncoder.encode("web123"))
            .authorizedGrantTypes("password", "refresh_token")
            .scopes("write", "read")
            .accessTokenValiditySeconds(60 * 60 * 6) // 6 horas (padrão é 12 horas)

            // type Legado, nao aconselhado usar pois passa o token pela url
            //http://localhost:8080/projeto-api/oauth/authorize?response_type=token&client_id=foodnanalytics&state=abc&redirect_uri=http://aplicacao-cliente
            .and()
            .withClient("webadmin")
            .authorizedGrantTypes("implicit")
            .scopes("write", "read")
            .redirectUris("http://aplicacao-cliente")

            // authorization_code
            //http://localhost:8080/projeto-api/oauth/authorize?response_type=code&client_id=foodnanalytics&state=abc&redirect_uri=http://aplicacao-cliente
            .and()
            .withClient("foodnanalytics")
            .secret(passwordEncoder.encode("food123"))
            .authorizedGrantTypes("authorization_code")
            .scopes("write", "read")
            .redirectUris("http://aplicacao-cliente")

            // client_credentials usado mais para processamento de alguma coisa dentro da Api sem interaçao com usuario
            .and()
            .withClient("faturamento")
            .secret(passwordEncoder.encode("faturamento123"))
            .authorizedGrantTypes("client_credentials")
            .scopes("write", "read")

            // usado para fazer a comunicação com o Resouce Serve URI de introspecção
            .and()
            .withClient("checktoken")
            .secret(passwordEncoder.encode("check123"));
    }

    /**
     * para permitir que seja usado o "check_token" http://localhost:8080/projeto-api/oauth/check_token
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.checkTokenAccess("isAuthenticated()");
        security.checkTokenAccess("permitAll()")
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
        endpoints
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
            // Implementando o suporte a PKCE com o fluxo Authorization Code
            .tokenGranter(tokenGranter(endpoints));
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

}
