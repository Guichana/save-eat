package save_eat.ports.out.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import save_eat.model.Eat;

public interface EatRepository extends CrudRepository<Eat, Integer> {

    List<Eat> findByUserId(Integer userId, Pageable pageable);

}