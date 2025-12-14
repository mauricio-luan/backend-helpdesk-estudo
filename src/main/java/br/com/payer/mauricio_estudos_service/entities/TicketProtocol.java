package br.com.payer.mauricio_estudos_service.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "ticket_protocol")
public class TicketProtocol {
    @MongoId
    private String id;
    private Long protocol;
}
