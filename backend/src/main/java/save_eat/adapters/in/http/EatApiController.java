package save_eat.adapters.in.http;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import save_eat.dto.eat.EatCreateDto;
import save_eat.dto.eat.EatCreateResultDto;
import save_eat.dto.eat.EatDataDto;
import save_eat.dto.eat.EatReadDto;
import save_eat.ports.in.usecase.eat.EatCreateUsecase;
import save_eat.ports.in.usecase.eat.EatReadUsecase;
import save_eat.security.UserPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/eat")
@RequiredArgsConstructor
public class EatApiController {

	private final EatCreateUsecase createService;
	private final EatReadUsecase readService;

	@PostMapping
	public EatCreateResultDto createEat(
		@AuthenticationPrincipal UserPrincipal principal,
		@RequestBody EatCreateDto createDto) {

		createDto.setUserId(principal.getUserId());
		return createService.create(createDto);
	}

	@GetMapping("{id}")
	public EatDataDto readEat(
		@AuthenticationPrincipal UserPrincipal principal,
		@PathVariable("id") Integer eatId) {
		return readService.read(
			EatReadDto.from(principal.getUserId(), eatId));
	}

}
