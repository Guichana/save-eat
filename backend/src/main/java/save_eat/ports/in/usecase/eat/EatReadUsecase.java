package save_eat.ports.in.usecase.eat;

import save_eat.dto.eat.EatDataDto;
import save_eat.dto.eat.EatReadDto;

public interface EatReadUsecase {
	EatDataDto read(EatReadDto readDto);
}
