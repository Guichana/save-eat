package save_eat.ports.in.usecase.user;

import save_eat.dto.user.OAuthLoginDto;
import save_eat.dto.user.OAuthLoginResultDto;

public interface OAuthLoginUsecase {
    OAuthLoginResultDto login(OAuthLoginDto request);
}
