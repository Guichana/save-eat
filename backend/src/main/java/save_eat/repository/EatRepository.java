package save_eat.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import save_eat.model.Eat;

public interface EatRepository extends JpaRepository<Eat, Integer> {

    List<Eat> findByUserId(Integer userId, Pageable pageable);

}