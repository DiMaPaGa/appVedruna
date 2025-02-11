package es.vedruna.appVedruna.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.vedruna.appVedruna.model.Comentario;
import es.vedruna.appVedruna.model.Publicacion;
import es.vedruna.appVedruna.repository.PuplicacionesRepository;



@Service
public class PublicacionServiceImpl implements PublicacionService{

    private final PuplicacionesRepository puplicacionesRepository;
    private final ComentarioServiceImpl comentarioServiceImpl;
    // Obtener todas las publicaciones
    public List<Publicacion> getAllPublicaciones() {
        
        List<Publicacion> publicaciones = puplicacionesRepository.findAllByOrderByCreatedAtDesc();

        for (Publicacion publicacion : publicaciones) {
            // Obtener los comentarios de esta publicación usando el servicio ComentarioServiceImpl
            List<Comentario> comentarios = comentarioServiceImpl.getAllComentarios(publicacion.getId());
            // Establecer los comentarios en la publicación
            publicacion.setComentarios(comentarios);
        }
        return publicaciones;
    }

     // Obtener publicaciones de un usuario específico por su userId
    public List<Publicacion> getPublicacionesPorUsuario(String userId) {

       
        // Obtener las publicaciones para un usuario específico
    
        List<Publicacion> publicaciones = puplicacionesRepository.findByUserId(userId);

        // Si no hay publicaciones, devolvemos una lista vacía (puedes manejarlo de otra manera si prefieres)
        if (publicaciones.isEmpty()) {
            return publicaciones;
        }

        // Si hay publicaciones, obtenemos los comentarios de cada una
        for (Publicacion publicacion : publicaciones) {
            List<Comentario> comentarios = comentarioServiceImpl.getAllComentarios(publicacion.getId());
            publicacion.setComentarios(comentarios);
        }
        return publicaciones;
    }

    @Override
    public Publicacion createPublicacion(Publicacion publicacion) {
        return puplicacionesRepository.save(publicacion);
    }

    @Override
    public Publicacion updateLike(String id_publicacion, String id_user) {
        Optional<Publicacion> publicacionOptional = puplicacionesRepository.findById(id_publicacion);
        
        if(publicacionOptional.isPresent()){
            Publicacion publicacion = publicacionOptional.get();
            // Añadir el id_user a la lista de likes si no está ya presente
            List<String> likes = publicacion.getLike();
            if (!likes.contains(id_user)) { // Evitar duplicados
                likes.add(id_user); // Agregar el id_user a la lista
                publicacion.setLike(likes); // Actualizar la lista de likes en la publicación
            }else {
                likes.remove(id_user); // Agregar el id_user a la lista
                publicacion.setLike(likes); // Actualizar la lista de likes en la publicación   
            }
            return puplicacionesRepository.save(publicacion);
        }else {
            throw new RuntimeException("Publicacion no encontrada");
        }
    }

    @Override
    public Optional<Publicacion> getById(String id_publicacion) {
        return puplicacionesRepository.findById(id_publicacion);
    }

    public PublicacionServiceImpl(PuplicacionesRepository puplicacionesRepository,
            ComentarioServiceImpl comentarioServiceImpl) {
        this.puplicacionesRepository = puplicacionesRepository;
        this.comentarioServiceImpl = comentarioServiceImpl;
    }

    public List<Publicacion> getPublicacionesLikedByUser(String userId) {
        return puplicacionesRepository.findByLikeContaining(userId);
    }

    

}
