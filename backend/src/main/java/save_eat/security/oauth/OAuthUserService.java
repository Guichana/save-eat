package save_eat.security.oauth;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import save_eat.dto.GetOAuthUserDTO;
import save_eat.service.UserService;

@Service
public class OAuthUserService {

    private OidcUserService oidcUserService = new OidcUserService();

    private UserService userService;

    public OAuthUserService(UserService userService) {
        this.userService = userService;
    }

    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = oidcUserService.loadUser(userRequest);
        String providerId = userRequest.getClientRegistration().getRegistrationId();

        Integer userId = userService.getOAuthUser(GetOAuthUserDTO.from(providerId, oidcUser));

        return new OAuthUserPrincipal.Oidc(oidcUser, userId);
    }

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        throw new UnsupportedOperationException("Unimplemented method 'loadUser'");
    }

}
