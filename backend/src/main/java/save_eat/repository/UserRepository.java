package save_eat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import save_eat.model.OAuth.OAuthCredential;
import save_eat.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByOauthIdsCredential(OAuthCredential credential);

}
