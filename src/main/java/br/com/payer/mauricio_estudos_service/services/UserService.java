package br.com.payer.mauricio_estudos_service.services;

import br.com.payer.mauricio_estudos_service.entities.User;
import br.com.payer.mauricio_estudos_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserRepository userRepository;

    //create
    public Mono<User> createUser(User user) {
        // validação de campos?
        return userRepository.insert(user);
    }

    //read
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    //update

    //delete
}
