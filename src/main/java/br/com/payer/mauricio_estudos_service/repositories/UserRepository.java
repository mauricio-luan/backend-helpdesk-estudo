package br.com.payer.mauricio_estudos_service.repositories;

import br.com.payer.mauricio_estudos_service.entities.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
