package br.com.payer.mauricio_estudos_service.services;

import br.com.payer.mauricio_estudos_service.entities.TicketProtocol;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TicketProtocolSequenceGenerator {
    private final ReactiveMongoOperations mongoOperations;

    public Mono<Long> generateNextProtocol(String seqName) {
        return mongoOperations.findAndModify(
                Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("protocol", 1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                TicketProtocol.class
                )
                .map(TicketProtocol::getProtocol);
    }
}
