package save_eat.dto.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.server.ResponseStatusException;

import lombok.Builder;
import save_eat.model.OAuth.OAuthCredential;
import save_eat.security.OAuthProvider;

@Builder
public class OAuthFindDto {

    private String providerId;
    private String uid;

    public OAuthCredential getOAuthCredential() {
        return new OAuthCredential(providerId, uid);
    }

    public static OAuthFindDto from(Authentication authentication) {
        if (JwtAuthenticationToken.class.isInstance(authentication)) {
            return from((JwtAuthenticationToken)authentication);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public static OAuthFindDto from(JwtAuthenticationToken authentication) {
        Jwt token = authentication.getToken();
        OAuthProvider provider = OAuthProvider.fromIssuer(token.getIssuer().toString());

        switch (provider) {
            case google:
                return OAuthFindDto.builder()
                    .providerId(provider.getProviderId())
                    .uid(token.getSubject())
                    .build();
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
