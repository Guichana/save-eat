package save_eat.dto.eat;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Setter;
import save_eat.model.Eat;

@Builder
public class EatCreateDto {

    @Setter
    private Integer userId;

    private String placeName;
    private LocalDateTime eatDate;
    private String eatName;
    private Short rating;
    private Integer price;
    private String comment;

    public Eat toEat() {
        return Eat.builder()
            .userId(userId)
            .placeName(placeName)
            .eatDate(eatDate)
            .eatName(eatName)
            .rating(rating)
            .price(price)
            .comment(comment)
            .build();
    }

}
