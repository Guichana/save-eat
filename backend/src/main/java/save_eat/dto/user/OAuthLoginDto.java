package save_eat.dto.user;

import lombok.Builder;
import save_eat.model.User;
import save_eat.model.OAuth.OAuthCredential;

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
}
