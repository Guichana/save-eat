package save_eat.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import save_eat.dto.eat.EatCreateDto;
import save_eat.dto.eat.EatCreateResultDto;
import save_eat.dto.eat.EatDataDto;
import save_eat.dto.eat.EatReadDto;
import save_eat.model.Eat;
import save_eat.ports.in.usecase.eat.EatCreateUsecase;
import save_eat.ports.in.usecase.eat.EatReadUsecase;
import save_eat.ports.out.repository.EatRepository;

@Service
@RequiredArgsConstructor
public class EatService implements EatCreateUsecase, EatReadUsecase {

    final private EatRepository eatRepository;

    public EatCreateResultDto create(EatCreateDto createDto) {
        Eat eat = eatRepository.save(createDto.toEat());

        return EatCreateResultDto.from(eat);
    }

    public EatDataDto read(EatReadDto readDto) {
        Eat eat = eatRepository
            .findByUserIdAndId(readDto.getUserId(), readDto.getEatId())
            .orElseThrow(); //TODO: notfound exception

        return EatDataDto.from(eat);
    }

}
