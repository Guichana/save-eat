package save_eat.dto.eat;

import java.io.Serializable;

import lombok.Builder;
import save_eat.model.Eat;

@Builder
public class EatCreateResultDto implements Serializable {
	private Integer eatId;

	public static EatCreateResultDto from(Eat eat) {
		return builder()
			.eatId(eat.getId())
			.build();
	}

	public Integer getEatId() {
		return eatId;
	}
}
