package es.vedruna.appVedruna.repository;

import es.vedruna.appVedruna.model.Usuario;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    @Query(value = "{ 'user_id' : ?0 }")
    Optional<Usuario> findByUserId(String user_id);
    Optional<Usuario> findByNick(String nick);
    

}
