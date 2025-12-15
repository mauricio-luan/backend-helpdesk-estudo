package br.com.payer.mauricio_estudos_service.services;

import br.com.payer.mauricio_estudos_service.entities.Ticket;
import br.com.payer.mauricio_estudos_service.enums.Priority;
import br.com.payer.mauricio_estudos_service.enums.Status;
import br.com.payer.mauricio_estudos_service.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketProtocolSequence ticketProtocolSequence;

    public Mono<Ticket> save(Ticket ticket) {
        return ticketProtocolSequence.generateNextProtocol("ticket_protocol")
                .flatMap(protocol -> {
                    if (ticket.getStatus() == null) {
                        ticket.setStatus(Status.OPEN);
                    }

                    if (ticket.getPriority() == null) {
                        ticket.setPriority(Priority.BAIXA);
                    }

                    if (ticket.getTitle() == null ||
                            ticket.getContent() == null ||
                            ticket.getCreatedByUserId() == null ||
                            ticket.getCreatedByUserEmail() == null
                    ) {
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Preencha os campos obrigatorios"
                        );
                    }

                    ticket.setProtocol(protocol);
                    ticket.setCreatedDate(LocalDateTime.now());

                    return ticketRepository.save(ticket);
                });
    }

    public Flux<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Mono<Ticket> getById(String id) {
        return ticketRepository.findById(id).
                switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Ticket não encontrado")
                ));
    }

    public Mono<Ticket> getTicketByProtocol(Long protocol) {
        return ticketRepository.findByProtocol(protocol)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Protocolo não encontrado"
                )));
    }

    public Mono<Ticket> update(String id, Ticket ticket) {
        return ticketRepository.findById(id)
                .flatMap(ticketBanco -> {
                            if (ticket.getTitle() != null) {
                                ticketBanco.setTitle(ticket.getTitle());
                            }
                            if (ticket.getContent() != null) {
                                ticketBanco.setContent(ticket.getContent());
                            }
                            if (ticket.getStatus() != null) {
                                ticketBanco.setStatus(ticket.getStatus());
                            }
                            return ticketRepository.save(ticketBanco);
                        }
                );
    }

    public Mono<Void> delete(String id) {
        return ticketRepository.deleteById(id);
    }
}
