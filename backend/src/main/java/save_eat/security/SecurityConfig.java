package save_eat.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import save_eat.security.oauth.OAuthUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, OAuthUserService oauthUserService)
		throws Exception {

		return http
			.authorizeHttpRequests((authorize) -> {
				authorize.anyRequest().permitAll();
			})
			.csrf(csrf -> {
				csrf.disable();
			})
			.headers(headers -> {
				headers.frameOptions(option -> {
					option.sameOrigin();
				});
			})
			.oauth2Login(oauth2 -> {
				oauth2.userInfoEndpoint(user -> {
					// user.userService(oAuthUserService::loadUser);
					user.oidcUserService(oauthUserService::loadUser);
				});
			})
			.build();
	}

}
