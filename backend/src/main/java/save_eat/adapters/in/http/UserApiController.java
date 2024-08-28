package save_eat.adapters.in.http;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import save_eat.dto.user.OAuthLoginDto;
import save_eat.dto.user.OAuthLoginResultDto;
import save_eat.dto.user.UserResponseDto;
import save_eat.ports.in.usecase.user.OAuthLoginUsecase;
import save_eat.ports.in.usecase.user.UserReadUsecase;
import save_eat.security.UserId;
import save_eat.security.UserPrincipal;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    final private UserReadUsecase users;
    final private OAuthLoginUsecase loginService;

    @GetMapping("/me")
    public UserResponseDto readUser(@UserId Integer userId) {
        return users.getUser(userId);
    }

    @PostMapping()
    public OAuthLoginResultDto join(Authentication authentication) {
        return loginService.login(
            OAuthLoginDto.from(authentication));
    }

}
