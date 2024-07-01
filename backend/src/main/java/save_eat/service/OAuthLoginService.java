package save_eat.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import save_eat.dto.user.OAuthLoginDto;
import save_eat.dto.user.OAuthLoginResultDto;
import save_eat.model.User;
import save_eat.ports.in.usecase.user.OAuthLoginUsecase;
import save_eat.ports.out.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class OAuthLoginService implements OAuthLoginUsecase {

    private final UserRepository userRepository;

    public OAuthLoginResultDto login(OAuthLoginDto loginDto) {
        User user = userRepository
            .findByOauthIdsCredential(loginDto.toOAuthCredential())
            .orElseGet(() -> userRepository.save(loginDto.toUser()));

        return OAuthLoginResultDto.from(user);
    }

}
