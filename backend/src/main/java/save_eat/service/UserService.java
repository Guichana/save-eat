package save_eat.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import save_eat.dto.UserOAuthRequest;
import save_eat.model.User;
import save_eat.model.OAuth.OAuthCredential;
import save_eat.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUser(Integer userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findUser(OAuthCredential oAuthCredential) {
        return userRepository.findByOauthIdsCredential(oAuthCredential);
    }

    @Transactional
    public User getOrCreateUser(UserOAuthRequest dto) {

        User user = findUser(dto.toOAuthCredential())
            .orElseGet(() -> userRepository.save(dto.toUser()));

        return user;
    }

}
