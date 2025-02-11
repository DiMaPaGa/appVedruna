package es.vedruna.appVedruna.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "tickets")
@Data 
@NoArgsConstructor 
@AllArgsConstructor

public class Ticket {

    @MongoId
    private String id;
    private String userNick;
    private String equipoClase;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private String estado;   
}
