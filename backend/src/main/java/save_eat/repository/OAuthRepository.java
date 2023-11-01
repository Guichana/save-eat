package save_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import save_eat.model.OAuth;

public interface OAuthRepository extends JpaRepository<OAuth, Integer> {

}
