package save_eat.adapters.out;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;

import lombok.RequiredArgsConstructor;
import save_eat.dto.storage.PhotoFileDto;
import save_eat.ports.out.FileStoragePort;

@RequiredArgsConstructor
public class LocalStorageAdapter implements FileStoragePort {

    private final Path dir;

    public void save(PhotoFileDto fileDto) {
        File dest = dir.resolve(fileDto.getFileName()).toFile();

        try (FileOutputStream writeStream = new FileOutputStream(dest)) {
            fileDto.getInputStream().transferTo(writeStream);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
