package save_eat.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(
		HttpSecurity http, OidcLoginService oidcLoginService)
		throws Exception {

		return http
			.authorizeHttpRequests((authorize) -> {
				authorize
					.requestMatchers(new AntPathRequestMatcher("/api/**")).authenticated()
					.anyRequest().permitAll();
			})
			.anonymous(anonymous -> {
				anonymous.disable();
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
					user.oidcUserService(oidcLoginService);
				});
			})
			.exceptionHandling(exception -> {
				exception.authenticationEntryPoint(new SecurityExceptionEntryPoint());
			})
			.logout(logout -> {
				logout.logoutSuccessUrl("/?logout");
			})
			.build();
	}

}
