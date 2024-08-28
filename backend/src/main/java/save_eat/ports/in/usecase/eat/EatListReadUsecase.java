package save_eat.ports.in.usecase.eat;

import save_eat.dto.eat.EatListDataDto;
import save_eat.dto.eat.EatListReadDto;

public interface EatListReadUsecase {
    EatListDataDto list(EatListReadDto readDto);
}
