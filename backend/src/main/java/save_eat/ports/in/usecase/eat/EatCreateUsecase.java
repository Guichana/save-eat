package save_eat.ports.in.usecase.eat;

import save_eat.dto.eat.EatCreateDto;
import save_eat.dto.eat.EatCreateResultDto;

public interface EatCreateUsecase {
	EatCreateResultDto create(EatCreateDto createDto);
}
