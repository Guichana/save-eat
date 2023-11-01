package save_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import save_eat.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findOneById(Integer id);

}
