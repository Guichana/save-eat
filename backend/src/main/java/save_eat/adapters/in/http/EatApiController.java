package save_eat.adapters.in.http;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import save_eat.dto.eat.EatCreateDto;
import save_eat.dto.eat.EatCreateResultDto;
import save_eat.dto.eat.EatDataDto;
import save_eat.dto.eat.EatDeleteDto;
import save_eat.dto.eat.EatListDataDto;
import save_eat.dto.eat.EatListReadDto;
import save_eat.dto.eat.EatReadDto;
import save_eat.dto.eat.PhotoAddDto;
import save_eat.ports.in.usecase.eat.EatCreateUsecase;
import save_eat.ports.in.usecase.eat.EatDeleteUsecase;
import save_eat.ports.in.usecase.eat.EatListReadUsecase;
import save_eat.ports.in.usecase.eat.EatReadUsecase;
import save_eat.ports.in.usecase.eat.PhotoAddUsecase;
import save_eat.security.UserId;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/eat")
@RequiredArgsConstructor
public class EatApiController {

	private final EatCreateUsecase createService;
	private final EatReadUsecase readService;
	private final EatListReadUsecase listService;
	private final EatDeleteUsecase deleteService;
	private final PhotoAddUsecase photoAddServce;

	@PostMapping
	public EatCreateResultDto createEat(
		@UserId Integer userId,
		@RequestBody EatCreateDto createDto) {

		createDto.setUserId(userId);
		return createService.create(createDto);
	}

	@PostMapping("{eatId}/photo")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addPhoto(
		@UserId Integer userId,
		@RequestPart("file") MultipartFile file,
		@PathVariable("eatId") Integer eatId) {

		PhotoAddDto addDto = new PhotoAddDto();
		addDto.setEatId(eatId);
		addDto.setFile(file);
		addDto.setUserId(userId);

		photoAddServce.addPhoto(addDto);

	}

	@GetMapping("{id}")
	public EatDataDto readEat(
		@UserId Integer userId,
		@PathVariable("id") Integer eatId) {
		return readService.read(
			EatReadDto.from(userId, eatId));
	}

	@GetMapping()
	public EatListDataDto readEatList(
		@UserId Integer userId,
		@RequestParam("page") Integer page) {

		EatListReadDto readDto = new EatListReadDto();
		readDto.setPage(page);
		readDto.setUserId(userId);
		readDto.setSize(10);

		return listService.list(readDto);
	}

	@DeleteMapping("{id}")
	public void deleteEat(
		@UserId Integer userId,
		@PathVariable("id") Integer eatId) {

		EatDeleteDto deleteDto = EatDeleteDto.from(userId, eatId);
		deleteService.delete(deleteDto);
	}

}
