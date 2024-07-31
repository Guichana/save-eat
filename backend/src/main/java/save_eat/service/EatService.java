package save_eat.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import save_eat.dto.eat.EatCreateDto;
import save_eat.dto.eat.EatCreateResultDto;
import save_eat.dto.eat.EatDataDto;
import save_eat.dto.eat.EatListDataDto;
import save_eat.dto.eat.EatListReadDto;
import save_eat.dto.eat.EatReadDto;
import save_eat.exception.ResourceNotFoundException;
import save_eat.model.Eat;
import save_eat.ports.in.usecase.eat.EatCreateUsecase;
import save_eat.ports.in.usecase.eat.EatListReadUsecase;
import save_eat.ports.in.usecase.eat.EatReadUsecase;
import save_eat.ports.out.repository.EatRepository;

@Service
@RequiredArgsConstructor
public class EatService implements EatCreateUsecase, EatReadUsecase, EatListReadUsecase {

    final private EatRepository eatRepository;

    public EatCreateResultDto create(EatCreateDto createDto) {
        Eat eat = eatRepository.save(createDto.toEat());
        return EatCreateResultDto.from(eat);
    }

    public EatDataDto read(EatReadDto readDto) {
        Eat eat = eatRepository
            .findByUserIdAndId(readDto.getUserId(), readDto.getEatId())
            .orElseThrow(ResourceNotFoundException::new);

        return EatDataDto.from(eat);
    }

    public EatListDataDto list(EatListReadDto readDto) {
        Page<Eat> list = eatRepository.findByUserId(readDto.getUserId(), readDto.getPageable());
        return EatListDataDto.from(list);
    }

}
