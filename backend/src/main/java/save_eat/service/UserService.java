package save_eat.service;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import save_eat.dto.GetOAuthUserDTO;
import save_eat.model.User;
import save_eat.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Integer getOAuthUser(GetOAuthUserDTO dto) {

        User user = userRepository.findByOauthIdsCredential(dto.toOAuthCredential())
            .orElseGet(() -> userRepository.save(dto.toUser()));

        return user.getId();
    }

}
