package save_eat.dto.eat;

import lombok.Setter;

@Setter
public class EatReadDto {

	private Integer userId;
	private Integer eatId;

	public Integer getUserId() {
		return userId;
	}

	public Integer getEatId() {
		return eatId;
	}

	public static EatReadDto from(Integer userId, Integer eatId) {
		EatReadDto readDto = new EatReadDto();
		readDto.setUserId(userId);
		readDto.setEatId(eatId);
		return readDto;
	}

}
