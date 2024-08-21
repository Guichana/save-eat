package save_eat.dto.eat;

import lombok.Builder;
import lombok.Setter;
import save_eat.dto.storage.PhotoFileDto;

@Setter
@Builder
public class PhotoAddDto {

    private Integer eatId;
    private Integer userId;
    private PhotoFileDto photoFile;

    public Integer getUserId() {
        return userId;
    }

    public Integer getEatId() {
        return eatId;
    }

    public PhotoFileDto getPhotoFile() {
        return photoFile;
    }

}
