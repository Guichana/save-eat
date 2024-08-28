package save_eat.dto.user;

import lombok.Builder;
import lombok.Getter;
import save_eat.model.User;

@Builder
@Getter
public class OAuthLoginResultDto {

	private Integer userId;

	public static OAuthLoginResultDto from(User user) {
		return builder()
			.userId(user.getId())
			.build();
	}

}
