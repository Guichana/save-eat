package save_eat.dto.eat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import save_eat.model.Eat;

@Builder
@Getter
public class EatDataDto implements Serializable {

	private Integer id;
	private String placeName;
	private String eatDate;
	private String foodName;
	private Short rating;
	private Integer price;
	private String comment;

	// private List<Tag> tags;
	// private List<Photo> photos;

	public static EatDataDto from(Eat eat) {
		return EatDataDto.builder()
			.id(eat.getId())
			.placeName(eat.getPlaceName())
			.eatDate(eat.getEatDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
			.foodName(eat.getFoodName())
			.rating(eat.getRating())
			.price(eat.getPrice())
			.comment(eat.getComment())
			.build();
	}

}
