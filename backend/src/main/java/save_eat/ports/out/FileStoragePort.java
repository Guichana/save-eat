package save_eat.ports.out;

import java.io.InputStream;

public interface FileStoragePort {

    String save(InputStream file, String name);

    String find(String name);

    void delete(String name);

}
