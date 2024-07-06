package save_eat.adapters.in.http;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import save_eat.dto.eat.EatCreateDto;
import save_eat.dto.eat.EatCreateResultDto;
import save_eat.ports.in.usecase.eat.EatCreateUsecase;
import save_eat.security.UserPrincipal;

@RestController
@RequestMapping("api/eat")
@RequiredArgsConstructor
public class EatApiController {

	private final EatCreateUsecase createService;

	@PostMapping
	EatCreateResultDto create(
		@AuthenticationPrincipal UserPrincipal principal,
		@RequestBody EatCreateDto createDto) {

		createDto.setUserId(principal.getUserId());
		return createService.create(createDto);
	}

}
