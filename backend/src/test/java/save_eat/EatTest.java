package save_eat;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import save_eat.dto.eat.EatCreateDto;
import save_eat.model.User;
import save_eat.ports.in.usecase.eat.EatCreateUsecase;
import save_eat.ports.out.repository.EatRepository;
import save_eat.ports.out.repository.UserRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class EatTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EatRepository eatRepository;

	@Autowired
	EatCreateUsecase eatService;

	Integer userId;

	@BeforeAll
	void prepareUser() {
		var user = User.builder()
			.name("")
			.email("")
			.imageUrl("")
			.build();

		userRepository.save(user);
		userId = user.getId();
	}

	String placeName = "placename";
	LocalDateTime eatDate = LocalDateTime.now();
	String foodName = "foodName";
	Short rating = 10;
	Integer price = 10000;
	String comment = "comment";

	@Test
	void addEat() {
		var createDto = new EatCreateDto();
		createDto.setUserId(userId);
		createDto.setPlaceName(placeName);
		createDto.setEatDate(eatDate);
		createDto.setFoodName(foodName);
		createDto.setRating(rating);
		createDto.setPrice(price);
		createDto.setComment(comment);

		var result = eatService.create(createDto);

		var eat = eatRepository.findById(result.getEatId()).get();
		var usersEat = eatRepository.findByUserId(userId, null);

		assertTrue(usersEat.contains(eat));
		assertEquals(eat.getPlaceName(), placeName);
		assertEquals(eat.getEatDate(), eatDate);
		assertEquals(eat.getFoodName(), foodName);
		assertEquals(eat.getRating(), rating);
		assertEquals(eat.getPrice(), price);
		assertEquals(eat.getComment(), comment);

	}

}
