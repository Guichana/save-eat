package save_eat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import save_eat.dto.user.OAuthLoginDto;
import save_eat.model.OAuth.OAuthCredential;
import save_eat.ports.in.usecase.user.OAuthLoginUsecase;
import save_eat.ports.out.repository.UserRepository;

@SpringBootTest
public class LoginTest {

	@Autowired
	OAuthLoginUsecase loginService;

	@Autowired
	UserRepository userRepository;

	final String providerId = "testprovider";
	final String uid = "testuid";
	final String username = "testusername";
	final String email = "test@email.com";
	final String imageUrl = "https://test/image.jpg";

	@Test
	void OAuthLoginTest() {

		var loginDto = OAuthLoginDto.builder()
			.providerId(providerId)
			.uid(uid)
			.username(username)
			.email(email)
			.imageUrl(imageUrl)
			.build();

		var result = loginService.login(loginDto);
		var result2 = loginService.login(loginDto);

		assertEquals(result.getUserId(), result2.getUserId());

		var user = userRepository.findByOauthIdsCredential(
			new OAuthCredential(providerId, uid)).get();

		assertEquals(user.getId(), result.getUserId());
		assertEquals(user.getName(), username);
		assertEquals(user.getImageUrl(), imageUrl);
		assertEquals(user.getEmail(), email);

	}

}
