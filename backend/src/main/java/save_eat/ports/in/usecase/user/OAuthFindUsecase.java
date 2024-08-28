package save_eat.ports.in.usecase.user;

import save_eat.dto.user.OAuthDataDto;
import save_eat.dto.user.OAuthFindDto;

public interface OAuthFindUsecase {

    OAuthDataDto find(OAuthFindDto findDto);

}
