package save_eat.security;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import save_eat.dto.user.OAuthLoginDto;
import save_eat.dto.user.OAuthLoginResultDto;
import save_eat.ports.in.usecase.user.OAuthLoginUsecase;

@Service
@RequiredArgsConstructor
public class OidcLoginService implements OAuth2UserService<OidcUserRequest, OidcUser> {

	private final OAuthLoginUsecase loginService;
	private OidcUserService oidcUserService = new OidcUserService();

	public OidcUser loadUser(OidcUserRequest request) {

		OidcUser oidcUser = oidcUserService.loadUser(request);
		OAuthLoginDto dto = OAuthLoginDto.builder()
			.providerId(request.getClientRegistration().getRegistrationId())
			.email(oidcUser.getEmail())
			.uid(oidcUser.getSubject())
			.username(oidcUser.getFullName())
			.imageUrl(oidcUser.getPicture())
			.build();

		OAuthLoginResultDto result = loginService.login(dto);

		return new OAuthUserPrincipal.Oidc(oidcUser, result.getUserId());
	}

}
