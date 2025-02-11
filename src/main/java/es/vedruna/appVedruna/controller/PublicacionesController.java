package es.vedruna.appVedruna.controller;


import es.vedruna.appVedruna.model.Comentario;
import es.vedruna.appVedruna.model.Publicacion;
import es.vedruna.appVedruna.services.PublicacionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyecto01/publicaciones")
@AllArgsConstructor
public class PublicacionesController {
    private final PublicacionServiceImpl publicacionServiceImpl;
 
    @PostMapping()
    public Publicacion createPublicacion(@RequestBody Publicacion publicacion) {
        System.out.println("Publicación a guardar: " + publicacion);
        return publicacionServiceImpl.createPublicacion(publicacion);
    }

    @PutMapping("/put/{id}/{id_user}")
    public Publicacion updateLike(@PathVariable String id, @PathVariable String id_user){
        return publicacionServiceImpl.updateLike(id, id_user);
    }

    @GetMapping()
    public List<Publicacion> getAllPublicaciones() {
        
        return publicacionServiceImpl.getAllPublicaciones();
    }

    // Nuevo endpoint para obtener publicaciones por userId
    @GetMapping("/user/{userId}")
    public List<Publicacion> getPublicacionesPorUsuario(@PathVariable String userId) {
        return publicacionServiceImpl.getPublicacionesPorUsuario(userId);
    }

    // Obtener todas las publicaciones donde el usuario ha dado "Me Gusta"
    @GetMapping("/liked/{userId}")
    public List<Publicacion> getPublicacionesLikedByUser(@PathVariable String userId) {
        return publicacionServiceImpl.getPublicacionesLikedByUser(userId);
}

}
