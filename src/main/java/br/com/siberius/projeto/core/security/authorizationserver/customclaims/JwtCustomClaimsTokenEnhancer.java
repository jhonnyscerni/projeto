package br.com.siberius.projeto.core.security.authorizationserver.customclaims;

import br.com.siberius.projeto.core.security.authorizationserver.userdetails.AuthUser;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

// Classe para customizar o Payload do JWT e incluir propriedades
public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (authentication.getPrincipal() instanceof AuthUser) {
            AuthUser authUser = (AuthUser) authentication.getPrincipal();

            Map<String, Object> info = new HashMap<String, Object>();
            info.put("nome_completo", authUser.getFullName());
            info.put("usuario_id", authUser.getUserId());
            info.put("grupo", authUser.getGrupos().get(0).getNome());

            DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
            oAuth2AccessToken.setAdditionalInformation(info);
        }

        return accessToken;
    }

}