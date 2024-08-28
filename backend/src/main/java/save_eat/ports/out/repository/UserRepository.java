package save_eat.ports.out.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import save_eat.model.User;
import save_eat.model.OAuth.OAuthCredential;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByOauthIdsCredential(OAuthCredential credential);

}
