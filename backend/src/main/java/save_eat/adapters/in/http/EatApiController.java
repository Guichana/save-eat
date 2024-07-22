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
import save_eat.dto.eat.EatReadDto;
import save_eat.dto.eat.PhotoAddDto;
import save_eat.ports.in.usecase.eat.EatCreateUsecase;
import save_eat.ports.in.usecase.eat.EatReadUsecase;
import save_eat.ports.in.usecase.eat.PhotoAddUsecase;
import save_eat.security.UserPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/eat")
@RequiredArgsConstructor
public class EatApiController {

	private final EatCreateUsecase createService;
	private final EatReadUsecase readService;
	private final PhotoAddUsecase photoAddServce;

	@PostMapping
	public EatCreateResultDto createEat(
		@AuthenticationPrincipal UserPrincipal principal,
		@RequestBody EatCreateDto createDto) {

		createDto.setUserId(principal.getUserId());
		return createService.create(createDto);
	}

	@PostMapping("{eatId}/photo")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addPhoto(
		@AuthenticationPrincipal UserPrincipal principal,
		@RequestPart("file") MultipartFile file,
		@PathVariable("eatId") Integer eatId) {

		PhotoAddDto addDto = new PhotoAddDto();
		addDto.setEatId(eatId);
		addDto.setFile(file);
		addDto.setUserId(principal.getUserId());

		photoAddServce.addPhoto(addDto);

	}

	@GetMapping("{id}")
	public EatDataDto readEat(
		@AuthenticationPrincipal UserPrincipal principal,
		@PathVariable("id") Integer eatId) {
		return readService.read(
			EatReadDto.from(principal.getUserId(), eatId));
	}

}
