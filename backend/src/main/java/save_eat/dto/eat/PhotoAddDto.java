package save_eat.dto.eat;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import lombok.Setter;

@Setter
public class PhotoAddDto {

    private MultipartFile file;
    private Integer eatId;
    private Integer userId;

    public InputStream getInputStream() {
        try {
            return file.getInputStream();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getEatId() {
        return eatId;
    }

}
