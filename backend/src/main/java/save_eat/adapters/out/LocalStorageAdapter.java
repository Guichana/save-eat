package save_eat.adapters.out;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.RequiredArgsConstructor;
import save_eat.ports.out.FileStoragePort;

@RequiredArgsConstructor
public class LocalStorageAdapter implements FileStoragePort {

    private final Path dir;
    private final String urlPrefix;

    public String save(InputStream file, String name) {
        try (FileOutputStream writeStream = new FileOutputStream(dir.resolve(name).toFile())) {
            file.transferTo(writeStream);
            return urlPrefix + name;
        } catch (Exception e) {
            throw new Error(e);
        }
    }


    @Override
    public String find(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public void delete(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
