package save_eat.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;

import save_eat.dto.UserResponse;
import save_eat.model.User;
import save_eat.security.UserPrincipal;
import save_eat.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    final private UserService userService;

    @GetMapping("/me")
    public UserResponse readUser(@AuthenticationPrincipal UserPrincipal userPrincipal, Authentication authentication) {

        User user = userService.findUser(userPrincipal.getUserId()).get();
        return UserResponse.from(user);
    }

}
