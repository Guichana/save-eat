package save_eat.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import save_eat.dto.eat.PhotoAddDto;
import save_eat.model.Eat;
import save_eat.model.Photo;
import save_eat.ports.in.usecase.eat.PhotoAddUsecase;
import save_eat.ports.out.FileStoragePort;
import save_eat.ports.out.repository.EatRepository;

@Service
@RequiredArgsConstructor
public class PhotoService implements PhotoAddUsecase {

    final private EatRepository eatRepository;
    final private FileStoragePort fileStoragePort;

    private Photo createPhoto(PhotoAddDto addDto) {
        String fileId = UUID.randomUUID().toString();
        fileStoragePort.save(addDto.getInputStream(), fileId);
        return new Photo(fileId);
    }

    public void addPhoto(PhotoAddDto addDto) {

        Eat eat = eatRepository
            .findByUserIdAndId(addDto.getUserId(), addDto.getEatId())
            .get();

        eat.addPhoto(createPhoto(addDto));
        eatRepository.save(eat);
    }

}
