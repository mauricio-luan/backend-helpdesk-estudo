package br.com.payer.mauricio_estudos_service.controllers;

import br.com.payer.mauricio_estudos_service.entities.User;
import br.com.payer.mauricio_estudos_service.services.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public Flux<User> getUsers() {
        return userService.getAllUsers();
    }
}
