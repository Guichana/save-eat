package save_eat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "RESOURCE_NOT_EXIST")
public class ResourceNotFoundException extends RuntimeException {
}
