package br.com.payer.mauricio_estudos_service.entities;

import br.com.payer.mauricio_estudos_service.enums.Priority;
import br.com.payer.mauricio_estudos_service.enums.Status;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document
@Data
public class Ticket {
    @MongoId
    private String id;
    private Long protocol;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private Status status;
    private Priority priority;
    private String createdByUserId;
    private String createdByUserEmail;
}
