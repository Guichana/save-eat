package save_eat.ports.out;

import save_eat.dto.storage.PhotoFileDto;

public interface FileStoragePort {

    void save(PhotoFileDto fileDto);

    // String find(String name);
    // void delete(String name);

}
