package es.vedruna.appVedruna.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Document(collection = "publicaciones")
@Data // Lombok genera los getters, setters, toString, etc.
@NoArgsConstructor // Lombok genera el constructor sin argumentos
@AllArgsConstructor // Lombok genera el constructor con todos los argumentos
public class Publicacion {
    @MongoId
    private String id;
    private String user_id;
    private String image_url;
    private String titulo;
    private String comentario;
    private List <String> like = new LinkedList<>();
    private LocalDateTime createdAt = LocalDateTime.now();

    // Este es el campo adicional para los comentarios.
    private List<Comentario> comentarios;

    // Aquí creamos un setter para poder asignar los comentarios en el servicio
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
