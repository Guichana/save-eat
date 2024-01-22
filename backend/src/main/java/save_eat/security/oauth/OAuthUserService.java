package save_eat.security.oauth;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import save_eat.dto.UserOAuthRequest;
import save_eat.model.User;
import save_eat.service.UserService;

@Service
@RequiredArgsConstructor
public class OAuthUserService {

    private OidcUserService oidcUserService = new OidcUserService();

    private final UserService userService;

    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = oidcUserService.loadUser(userRequest);

        UserOAuthRequest dto = UserOAuthRequest.builder()
            .providerId(userRequest.getClientRegistration().getRegistrationId())
            .email(oidcUser.getEmail())
            .uid(oidcUser.getSubject())
            .username(oidcUser.getFullName())
            .imageUrl(oidcUser.getPicture())
            .build();

        User user = userService.getOrCreateUser(dto);

        return new OAuthUserPrincipal.Oidc(oidcUser, user.getId());
    }

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        throw new UnsupportedOperationException("Unimplemented method 'loadUser'");
    }

}
