package save_eat.dto.eat;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.Setter;

@Setter
public class EatListReadDto {

    private Integer userId;
    private Integer page;
    private Integer size;

    public Integer getUserId() {
        return userId;
    }

    public Pageable getPageable() {
        return PageRequest.of(page, size);
    }

}
