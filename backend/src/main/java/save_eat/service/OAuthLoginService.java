package save_eat.service;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import lombok.RequiredArgsConstructor;

import save_eat.dto.OAuthLoginDto;
import save_eat.model.User;
import save_eat.ports.in.usecase.user.OAuthLoginUsecase;
import save_eat.ports.out.repository.UserRepository;
import save_eat.security.OAuthUserPrincipal;

@RequiredArgsConstructor
public class OAuthLoginService implements OAuthLoginUsecase {

    private final UserRepository userRepository;
    private OidcUserService oidcUserService = new OidcUserService();

    public User login(OAuthLoginDto loginDto) {
        return userRepository
            .findByOauthIdsCredential(loginDto.toOAuthCredential())
            .orElseGet(() -> userRepository.save(loginDto.toUser()));
    }

    public OidcUser loadUser(OidcUserRequest request) {
        OidcUser oidcUser = oidcUserService.loadUser(request);
        OAuthLoginDto dto = OAuthLoginDto.builder()
            .providerId(request.getClientRegistration().getRegistrationId())
            .email(oidcUser.getEmail())
            .uid(oidcUser.getSubject())
            .username(oidcUser.getFullName())
            .imageUrl(oidcUser.getPicture())
            .build();

        User user = login(dto);

        return new OAuthUserPrincipal.Oidc(oidcUser, user.getId());
    }

}
