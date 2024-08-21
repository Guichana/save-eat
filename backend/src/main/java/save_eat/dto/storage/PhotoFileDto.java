package save_eat.dto.storage;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;

@Builder
public class PhotoFileDto {

    private String filename;
    private long size;
    private String contentType;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public long getSize() {
        return size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileName() {
        return filename;
    }

    static public PhotoFileDto from(MultipartFile file) {
        try {
            return builder()
                .size(file.getSize())
                .inputStream(file.getInputStream())
                .contentType(file.getContentType())
                .build();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

}
