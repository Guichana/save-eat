package save_eat.dto.user;

import lombok.Builder;
import lombok.Getter;
import save_eat.model.OAuth;

@Builder
public class OAuthDataDto {

    @Getter
    private Integer userId;

    public static OAuthDataDto from(OAuth oauth) {
        return builder()
            .userId(oauth.getUserId())
            .build();
    }

}
