package save_eat.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import save_eat.ports.in.usecase.user.OAuthFindUsecase;

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
			// .oauth2Login(oauth2 -> {
			// 	oauth2.userInfoEndpoint(user -> {
			// 		// user.userService(oAuthUserService::loadUser);
			// 		user.oidcUserService(oidcLoginService);
			// 	});
			// })
			.oauth2ResourceServer((oauth2) -> {
				oauth2.jwt(Customizer.withDefaults());

			})
			.exceptionHandling(exception -> {
				exception.authenticationEntryPoint(new SecurityExceptionEntryPoint());
			})
			.logout(logout -> {
				logout.logoutSuccessUrl("/?logout");
			})
			.build();
	}

	@Bean
	WebMvcConfigurer addArgumentResolver(OAuthFindUsecase oauthService) {
		return new WebMvcConfigurer() {
			public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
				resolvers.add(new UserIdArgumentResolver(oauthService));
			}
		};
	}

}
