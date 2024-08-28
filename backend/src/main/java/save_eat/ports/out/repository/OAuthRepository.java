package save_eat.ports.out.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import save_eat.model.OAuth;
import save_eat.model.OAuth.OAuthCredential;

public interface OAuthRepository extends CrudRepository<OAuth, Integer> {

    Optional<OAuth> findByCredential(OAuthCredential credential);

}
