package save_eat.ports.in.usecase.user;

import save_eat.dto.user.UserResponseDto;

public interface UserReadUsecase {
    UserResponseDto getUser(Integer userId);
}
