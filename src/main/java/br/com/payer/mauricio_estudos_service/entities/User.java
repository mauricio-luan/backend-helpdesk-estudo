package br.com.payer.mauricio_estudos_service.entities;

import br.com.payer.mauricio_estudos_service.enums.Department;
import br.com.payer.mauricio_estudos_service.enums.Profile;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
public class User {
    @MongoId
    private String id;
    private String username;
    private String email;
    private Profile profile;
    private Department department;
}
