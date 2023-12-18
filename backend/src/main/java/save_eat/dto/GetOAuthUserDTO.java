package save_eat.dto;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import lombok.Builder;
import lombok.Getter;
import save_eat.model.OAuth;
import save_eat.model.User;

@Getter
public class GetOAuthUserDTO {

    String providerId;
    String uid;
    String email;
    String username;

    @Builder
    GetOAuthUserDTO(String providerId, String uid, String email, String username) {
        this.providerId = providerId;
        this.uid = uid;
        this.email = email;
        this.username = username;
    }

    public static GetOAuthUserDTO from(String providerId, OidcUser oidcUser) {
        return builder()
            .providerId(providerId)
            .uid(oidcUser.getName())
            .email(oidcUser.getEmailVerified() ? oidcUser.getEmail() : null)
            .username(oidcUser.getFullName())
            .build();
    }

    public OAuth.Credential toOAuthCredential() {
        return new OAuth.Credential(providerId, uid);
    }

    public User toUser() {
        return User.builder()
            .name(username)
            .email(email)
            .oauthId(toOAuthCredential())
            .build();
    }

}
