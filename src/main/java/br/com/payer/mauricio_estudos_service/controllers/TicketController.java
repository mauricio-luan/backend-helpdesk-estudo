package br.com.payer.mauricio_estudos_service.controllers;

import br.com.payer.mauricio_estudos_service.entities.Ticket;
import br.com.payer.mauricio_estudos_service.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Ticket> create(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }

    @GetMapping
    public Flux<Ticket> getAll() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Mono<Ticket> getById(@PathVariable String id) {
        return ticketService.getById(id);
    }

    @PutMapping("/{id}")
    public Mono<Ticket> update(@PathVariable String id, @RequestBody Ticket ticket) {
        return ticketService.update(id, ticket);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return ticketService.delete(id);
    }
}
