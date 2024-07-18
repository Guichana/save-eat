package save_eat;

import static org.junit.jupiter.api.Assertions.*;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import save_eat.dto.eat.EatCreateDto;
import save_eat.dto.eat.EatReadDto;
import save_eat.dto.eat.PhotoAddDto;
import save_eat.model.Eat;
import save_eat.model.User;
import save_eat.ports.in.usecase.eat.EatCreateUsecase;
import save_eat.ports.in.usecase.eat.EatReadUsecase;
import save_eat.ports.in.usecase.eat.PhotoAddUsecase;
import save_eat.ports.out.FileStoragePort;
import save_eat.ports.out.repository.EatRepository;
import save_eat.ports.out.repository.UserRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@SpringJUnitConfig
public class EatTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EatRepository eatRepository;

	@Autowired
	EatCreateUsecase eatCreateService;

	@Autowired
	EatReadUsecase eatReadService;

	@Autowired
	PhotoAddUsecase photoAddService;

	@MockBean
	FileStoragePort storageService;

	Integer userId;
	Eat eat;

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
	String eatDate = "2024-01-01";
	String foodName = "foodName";
	Short rating = 10;
	Integer price = 10000;
	String comment = "comment";
	List<String> tags = Arrays.asList("TEST1", "TEST2");

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
		createDto.setTags(tags);

		var result = eatCreateService.create(createDto);

		var eat = eatRepository.findById(result.getEatId()).get();
		var usersEat = eatRepository.findByUserId(userId, null);

		assertTrue(usersEat.contains(eat));
		assertEquals(eat.getPlaceName(), placeName);
		assertTrue(eat.getEatDate().isEqual(LocalDate.parse(eatDate, DateTimeFormatter.ISO_LOCAL_DATE)));
		assertEquals(eat.getFoodName(), foodName);
		assertEquals(eat.getRating(), rating);
		assertEquals(eat.getPrice(), price);
		assertEquals(eat.getComment(), comment);
		assertArrayEquals(eat.getTags().toArray(), tags.toArray());

		this.eat = eat;

	}

	@Test
	void addPhotoTest() {
		var addDto = new PhotoAddDto() {
			@Override
			public Integer getUserId() {
				return userId;
			}

			@Override
			public Integer getEatId() {
				return eat.getId();
			}

			@Override
			public InputStream getInputStream() {
				return InputStream.nullInputStream();
			}
		};

		photoAddService.addPhoto(addDto);

		var eat = eatRepository.findById(this.eat.getId()).get();
		this.eat = eat;
		assertEquals(eat.getPhotos().size(), 1);
	}

	@Test
	void readEatTest() {

		var readDto = EatReadDto.from(userId, eat.getId());
		var eatDto = eatReadService.read(readDto);

		assertEquals(eat.getId(), eatDto.getId());
		assertEquals(eat.getPlaceName(), eatDto.getPlaceName());
		assertTrue(eat.getEatDate().isEqual(LocalDate.parse(eatDto.getEatDate(), DateTimeFormatter.ISO_LOCAL_DATE)));
		assertEquals(eat.getFoodName(), eatDto.getFoodName());
		assertEquals(eat.getRating(), eatDto.getRating());
		assertEquals(eat.getPrice(), eatDto.getPrice());
		assertEquals(eat.getComment(), eatDto.getComment());
		assertEquals(eat.getTags(), eatDto.getTags());
		assertEquals(eat.getPhotos().stream().map(photo -> photo.getFileId()).toList(), eatDto.getPhotos());

		readDto.setUserId(0);

		assertThrows(NoSuchElementException.class, () -> eatReadService.read(readDto));

	}

}
