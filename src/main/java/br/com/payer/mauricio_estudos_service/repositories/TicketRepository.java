package br.com.payer.mauricio_estudos_service.repositories;

import br.com.payer.mauricio_estudos_service.entities.Ticket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TicketRepository extends ReactiveMongoRepository<Ticket, String> {

    public Mono<Ticket> findByProtocol(Long protocol);
}
