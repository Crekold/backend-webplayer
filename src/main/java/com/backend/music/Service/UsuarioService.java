package com.backend.music.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.music.Model.Usuario;
import com.backend.music.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario register(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String nombreUsuario, String contrasena) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (usuario != null && contrasena.equals(usuario.getContrasena())) {
            return usuario;
        }
        return null;
    }
}