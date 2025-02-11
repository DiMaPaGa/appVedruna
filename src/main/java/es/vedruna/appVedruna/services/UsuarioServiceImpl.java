package es.vedruna.appVedruna.services;

import es.vedruna.appVedruna.model.Usuario;
import es.vedruna.appVedruna.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    // Crear un nuevo usuario
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioByUserId(String user_id) {
        Optional<Usuario> usuario = usuarioRepository.findByUserId(user_id);
        return usuario.isPresent() ? usuario.get() : null;
    }

     // Actualizar la imagen de perfil
     public Usuario updateProfileImage(String user_id, String profilePicture) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUserId(user_id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setProfile_picture(profilePicture);
            return usuarioRepository.save(usuario);
        }
        return null; // Si no se encuentra el usuario, retorna null
    }

    public Usuario getUsuarioByNick(String nick) {
        return usuarioRepository.findByNick(nick)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
