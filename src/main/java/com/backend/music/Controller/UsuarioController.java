package com.backend.music.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.music.Model.Usuario;
import com.backend.music.Service.UsuarioService;
import com.backend.music.message.ResponseMessage;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
@PostMapping("/registro")
public ResponseEntity<?> register(@RequestBody Usuario usuario) {
    Usuario newUser = usuarioService.register(usuario);
    if (newUser != null) {
        return new ResponseEntity<>(new ResponseMessage("Usuario registrado con éxito"), HttpStatus.CREATED);
    } else {
        return new ResponseEntity<>(new ResponseMessage("Error al registrar el usuario"), HttpStatus.BAD_REQUEST);
    }
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Usuario usuario) {
    Usuario existingUser = usuarioService.login(usuario.getNombreUsuario(), usuario.getContrasena());
    if (existingUser != null) {
        return new ResponseEntity<>(new ResponseMessage("Inicio de sesión exitoso"), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(new ResponseMessage("Error en el inicio de sesión"), HttpStatus.UNAUTHORIZED);
    }
}
}