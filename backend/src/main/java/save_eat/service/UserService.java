package save_eat.service;

import org.springframework.stereotype.Service;

import save_eat.dto.UserResponseDto;
import save_eat.ports.in.usecase.user.UserReadUsecase;
import save_eat.ports.out.repository.UserRepository;

@Service
public class UserService implements UserReadUsecase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto getUser(Integer userId) {
        return userRepository
            .findById(userId)
            .map(UserResponseDto::from)
            .get();
    }

}
