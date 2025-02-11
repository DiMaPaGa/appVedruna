package es.vedruna.appVedruna.repository;

import es.vedruna.appVedruna.model.Publicacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuplicacionesRepository extends MongoRepository<Publicacion, String> {
    List<Publicacion> findAllByOrderByCreatedAtDesc();
     @Query("{ 'user_id' : ?0 }")
    List<Publicacion> findByUserId(String userId);
    List<Publicacion> findByLikeContaining(String userId);
}
