package com.backend.music.Repository;

import com.backend.music.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByNombreUsuario(String nombreUsuario);
}