package save_eat.adapters.in.http;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;

import save_eat.dto.UserResponseDto;
import save_eat.ports.in.usecase.user.UserReadUsecase;
import save_eat.security.UserPrincipal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    final private UserReadUsecase users;

    @GetMapping("/me")
    public UserResponseDto readUser(@AuthenticationPrincipal
    UserPrincipal userPrincipal, Authentication authentication) {
        return users.getUser(userPrincipal.getUserId());
    }

}
