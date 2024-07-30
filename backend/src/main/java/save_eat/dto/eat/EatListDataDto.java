package save_eat.dto.eat;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import save_eat.model.Eat;
import save_eat.model.Photo;

@Getter
@Builder
public class EatListDataDto implements Serializable {

    private List<EatItem> list;

    public static EatListDataDto from(List<Eat> list) {
        return builder()
            .list(list.stream().map(EatItem::from).toList())
            .build();

    }

    @Builder
    @Getter
    public static class EatItem implements Serializable {
        private Integer id;
        private String placeName;
        private String eatDate;
        private String foodName;
        private Short rating;
        private String photo;

        public static EatItem from(Eat eat) {
            return builder()
                .id(eat.getId())
                .placeName(eat.getPlaceName())
                .eatDate(eat.getEatDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .foodName(eat.getFoodName())
                .rating(eat.getRating())
                .photo(
                    eat.getPhotos().stream()
                        .findFirst()
                        .map(Photo::getFileId)
                        .orElse(null))
                .build();
        }

    }

}
