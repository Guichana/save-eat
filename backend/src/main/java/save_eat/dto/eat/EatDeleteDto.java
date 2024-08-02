package save_eat.dto.eat;

import lombok.Setter;

@Setter
public class EatDeleteDto {

    private Integer userId;
    private Integer eatId;

    public Integer getUserId() {
        return userId;
    }

    public Integer getEatId() {
        return eatId;
    }

    static public EatDeleteDto from(Integer userId, Integer eatId) {
        EatDeleteDto dto = new EatDeleteDto();
        dto.setUserId(userId);
        dto.setEatId(eatId);
        return dto;
    }

}
