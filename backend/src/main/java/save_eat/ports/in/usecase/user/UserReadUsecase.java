package save_eat.ports.in.usecase.user;

import save_eat.dto.UserResponseDto;

public interface UserReadUsecase {
    UserResponseDto getUser(Integer userId);
}
