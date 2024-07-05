package save_eat.dto.eat;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Setter;

import save_eat.model.Eat;

@NoArgsConstructor
@Setter
public class EatCreateDto {

    private Integer userId;

    @JsonProperty
    private String placeName;

    @JsonProperty
    private LocalDateTime eatDate;

    @JsonProperty
    private String foodName;

    @JsonProperty
    private Short rating;

    @JsonProperty
    private Integer price;

    @JsonProperty
    private String comment;

    public Eat toEat() {
        //TODO: validation 추가
        return Eat.builder()
            .userId(userId)
            .placeName(placeName)
            .eatDate(eatDate)
            .foodName(foodName)
            .rating(rating)
            .price(price)
            .comment(comment)
            .build();
    }

}
