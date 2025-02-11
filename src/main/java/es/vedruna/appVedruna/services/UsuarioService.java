package es.vedruna.appVedruna.services;

import es.vedruna.appVedruna.model.Usuario;

import java.util.List;

public interface UsuarioService {

    public Usuario createUsuario(Usuario usuario);
    public List<Usuario> getAllUsuarios();
    public Usuario getUsuarioByUserId(String user_id);
    public Usuario updateProfileImage(String user_id, String profilePicture);
    public Usuario getUsuarioByNick(String nick);
    
}
