package save_eat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import save_eat.dto.eat.EatCreateDto;
import save_eat.dto.eat.EatDeleteDto;
import save_eat.dto.eat.EatListReadDto;
import save_eat.dto.eat.EatReadDto;
import save_eat.dto.eat.PhotoAddDto;
import save_eat.dto.storage.PhotoFileDto;
import save_eat.exception.ResourceNotFoundException;
import save_eat.model.Eat;
import save_eat.model.User;
import save_eat.ports.in.usecase.eat.EatCreateUsecase;
import save_eat.ports.in.usecase.eat.EatDeleteUsecase;
import save_eat.ports.in.usecase.eat.EatListReadUsecase;
import save_eat.ports.in.usecase.eat.EatReadUsecase;
import save_eat.ports.in.usecase.eat.PhotoAddUsecase;
import save_eat.ports.out.PhotoStoragePort;
import save_eat.ports.out.repository.EatRepository;
import save_eat.ports.out.repository.UserRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@SpringJUnitConfig
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
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

	@Autowired
	EatListReadUsecase eatListReadService;

	@Autowired
	EatDeleteUsecase eatDeleteService;

	@MockBean
	PhotoStoragePort storageService;

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
	@Order(1)
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

		assertTrue(usersEat.toList().contains(eat));
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
	@Order(2)
	void addPhotoTest() {
		var addDto = PhotoAddDto.builder()
			.userId(userId)
			.eatId(eat.getId())
			.photoFile(
				PhotoFileDto.builder()
					.inputStream(InputStream.nullInputStream())
					.build())
			.build();

		photoAddService.addPhoto(addDto);
		verify(storageService).save(addDto.getPhotoFile());

		var eat = eatRepository.findById(this.eat.getId()).get();
		this.eat = eat;
		assertEquals(eat.getPhotos().size(), 1);
	}

	@Test
	@Order(3)
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

		assertThrows(ResourceNotFoundException.class, () -> eatReadService.read(readDto));

	}

	@Test
	@Order(4)
	void readEatListTest() {
		var readDto = new EatListReadDto();
		readDto.setPage(0);
		readDto.setSize(1);
		readDto.setUserId(userId);

		var result = eatListReadService.list(readDto);
		var list = result.getList();
		assertEquals(list.size(), 1);
		assertEquals(result.getHasNext(), false);

		var sample = list.get(0);
		assertEquals(eat.getId(), sample.getId());
		assertEquals(eat.getPlaceName(), sample.getPlaceName());
		assertTrue(eat.getEatDate().isEqual(LocalDate.parse(sample.getDate(), DateTimeFormatter.ISO_LOCAL_DATE)));
		assertEquals(eat.getFoodName(), sample.getFoodName());
		assertEquals(eat.getRating(), sample.getRating());
		assertEquals(eat.getPhotos().get(0).getFileId(), sample.getThumbnail());

		readDto.setPage(1);
		var result2 = eatListReadService.list(readDto);
		assertEquals(result2.getList().size(), 0);

	}

	@Test
	@Order(5)
	void deleteEatTest() {
		var deleteDto = EatDeleteDto.from(userId, eat.getId());
		eatDeleteService.delete(deleteDto);

		verify(storageService).delete(anyString());
		var readDto = EatReadDto.from(userId, eat.getId());
		assertThrows(ResourceNotFoundException.class, () -> eatReadService.read(readDto));
	}

}
