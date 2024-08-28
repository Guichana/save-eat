package save_eat.dto.eat;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Getter;
import save_eat.model.Eat;
import save_eat.model.Photo;

@Getter
@Builder
public class EatListDataDto implements Serializable {

    private List<EatItem> list;
    private Boolean hasNext;

    public static EatListDataDto from(Page<Eat> list) {
        return builder()
            .list(list.stream().map(EatItem::from).toList())
            .hasNext(list.hasNext())
            .build();

    }

    @Builder
    @Getter
    public static class EatItem implements Serializable {
        private Integer id;
        private String placeName;
        private String date;
        private String foodName;
        private Short rating;
        private String thumbnail;

        public static EatItem from(Eat eat) {
            return builder()
                .id(eat.getId())
                .placeName(eat.getPlaceName())
                .date(eat.getEatDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .foodName(eat.getFoodName())
                .rating(eat.getRating())
                .thumbnail(
                    eat.getPhotos().stream()
                        .findFirst()
                        .map(Photo::getFileId)
                        .orElse(null))
                .build();
        }

    }

}
