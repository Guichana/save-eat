package save_eat.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.PostRemove;
import lombok.RequiredArgsConstructor;
import save_eat.ports.out.PhotoStoragePort;

@Component
@RequiredArgsConstructor
public class PhotoListener {

    final private PhotoStoragePort photoStorage;

    @PostRemove
    void postRemove(Photo photo) {
        photoStorage.delete(photo.getFileId());
    }

}
