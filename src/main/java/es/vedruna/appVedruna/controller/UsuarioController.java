package es.vedruna.appVedruna.controller;

import es.vedruna.appVedruna.model.Usuario;
import es.vedruna.appVedruna.services.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyecto01/users")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioServiceImpl;

    // Crear un nuevo usuario
    @PostMapping()
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioServiceImpl.createUsuario(usuario);
    }

    // Obtener todos los usuarios
    @GetMapping("/name")
    public List<Usuario> getAllUsuarios() {
        return usuarioServiceImpl.getAllUsuarios();
    }

    // Obtener un usuario por ID
    @GetMapping("/{user_id}")
    public Usuario getUsuarioById(@PathVariable String user_id) {
        return usuarioServiceImpl.getUsuarioByUserId(user_id);
    }
    @GetMapping("/nick/{nick}")
    public Usuario getUsuarioByNick(@PathVariable String nick) {
        return usuarioServiceImpl.getUsuarioByNick(nick);
    }

     // Actualizar imagen de perfil
     @PutMapping("/{user_id}")
     public Usuario updateProfileImage(@PathVariable String user_id, @RequestBody Usuario usuario) {
         return usuarioServiceImpl.updateProfileImage(user_id, usuario.getProfile_picture());
     }


    

}
