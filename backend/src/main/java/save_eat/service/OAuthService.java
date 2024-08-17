package save_eat.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import save_eat.dto.user.OAuthDataDto;
import save_eat.dto.user.OAuthFindDto;
import save_eat.dto.user.OAuthLoginDto;
import save_eat.dto.user.OAuthLoginResultDto;
import save_eat.exception.ResourceNotFoundException;
import save_eat.model.User;
import save_eat.ports.in.usecase.user.OAuthFindUsecase;
import save_eat.ports.in.usecase.user.OAuthLoginUsecase;
import save_eat.ports.out.repository.OAuthRepository;
import save_eat.ports.out.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class OAuthService implements OAuthLoginUsecase, OAuthFindUsecase {

    private final UserRepository userRepository;
    private final OAuthRepository oauthRepository;

    public OAuthLoginResultDto login(OAuthLoginDto loginDto) {
        User user = userRepository
            .findByOauthIdsCredential(loginDto.toOAuthCredential())
            .orElseGet(() -> userRepository.save(loginDto.toUser()));

        return OAuthLoginResultDto.from(user);
    }

    public OAuthDataDto find(OAuthFindDto findDto) {
        return oauthRepository
            .findByCredential(findDto.getOAuthCredential())
            .map(OAuthDataDto::from)
            .orElseThrow(ResourceNotFoundException::new);
    }

}
