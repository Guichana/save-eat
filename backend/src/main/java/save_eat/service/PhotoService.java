package save_eat.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import save_eat.dto.eat.PhotoAddDto;
import save_eat.dto.storage.PhotoFileDto;
import save_eat.model.Eat;
import save_eat.model.Photo;
import save_eat.ports.in.usecase.eat.PhotoAddUsecase;
import save_eat.ports.out.PhotoStoragePort;
import save_eat.ports.out.repository.EatRepository;

@Service
@RequiredArgsConstructor
public class PhotoService implements PhotoAddUsecase {

    final private EatRepository eatRepository;
    final private PhotoStoragePort fileStoragePort;

    private Photo createPhoto(PhotoFileDto photoFileDto) {
        String fileId = UUID.randomUUID().toString();
        photoFileDto.setFilename(fileId);
        fileStoragePort.save(photoFileDto);
        return new Photo(fileId);
    }

    public void addPhoto(PhotoAddDto addDto) {

        Eat eat = eatRepository
            .findByUserIdAndId(addDto.getUserId(), addDto.getEatId())
            .get();

        Photo photo = createPhoto(addDto.getPhotoFile());

        eat.addPhoto(photo);
        eatRepository.save(eat);
    }

}
