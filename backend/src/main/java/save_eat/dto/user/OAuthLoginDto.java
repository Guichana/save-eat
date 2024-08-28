package save_eat.dto.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.ClaimAccessor;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimAccessor;
import org.springframework.security.oauth2.core.oidc.StandardClaimAccessor;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.server.ResponseStatusException;

import lombok.Builder;
import save_eat.model.User;
import save_eat.model.OAuth.OAuthCredential;
import save_eat.security.OAuthProvider;

@Builder
public class OAuthLoginDto {

    private String providerId;
    private String uid;
    private String username;
    private String email;
    private String imageUrl;

    public OAuthCredential toOAuthCredential() {
        return new OAuthCredential(providerId, uid);
    }

    public User toUser() {
        return User.builder()
            .name(username)
            .email(email)
            .imageUrl(imageUrl)
            .oauthId(toOAuthCredential())
            .build();
    }

    public static OAuthLoginDto from(Authentication authentication) {
        if (JwtAuthenticationToken.class.isInstance(authentication)) {
            return from((JwtAuthenticationToken)authentication);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public static OAuthLoginDto from(JwtAuthenticationToken authentication) {
        Jwt token = authentication.getToken();
        OAuthProvider provider = OAuthProvider.fromIssuer(token.getIssuer().toString());

        switch (provider) {
            case google:
                return OAuthLoginDto.builder()
                    .providerId(provider.getProviderId())
                    .uid(token.getSubject())
                    .email(token.getClaimAsString(StandardClaimNames.EMAIL))
                    .username(token.getClaimAsString(StandardClaimNames.NAME))
                    .username(token.getClaimAsString(StandardClaimNames.PICTURE))
                    .build();
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
