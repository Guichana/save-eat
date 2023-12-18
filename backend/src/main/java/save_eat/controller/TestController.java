package save_eat.controller;

import org.springframework.web.bind.annotation.RestController;
import save_eat.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TestController {

    @GetMapping("test")
    public String getMethodName(@AuthenticationPrincipal UserPrincipal user) {

        System.out.println(user.getUserId());

        return "test";
    }

}
