package save_eat.ports.in.usecase.user;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface OAuthLoginUsecase {
    OidcUser loadUser(OidcUserRequest request);
}
