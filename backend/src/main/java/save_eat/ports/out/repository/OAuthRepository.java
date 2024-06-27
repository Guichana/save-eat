package save_eat.ports.out.repository;

import org.springframework.data.repository.CrudRepository;

import save_eat.model.OAuth;

public interface OAuthRepository extends CrudRepository<OAuth, Integer> {

}
