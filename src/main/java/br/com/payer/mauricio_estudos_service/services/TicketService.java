package br.com.payer.mauricio_estudos_service.services;

import br.com.payer.mauricio_estudos_service.entities.Ticket;
import br.com.payer.mauricio_estudos_service.enums.Status;
import br.com.payer.mauricio_estudos_service.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public Mono<Ticket> save(Ticket ticket) {
        ticket.setTimestamp(LocalDateTime.now());

        if (ticket.getStatus() == null) {
            ticket.setStatus(Status.OPEN);
        }

        return ticketRepository.save(ticket);
    }

    public Flux<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Mono<Ticket> getById(String id) {
        return ticketRepository.findById(id);
    }
}
