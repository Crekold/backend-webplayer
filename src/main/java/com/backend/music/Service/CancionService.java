package com.backend.music.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import com.backend.music.Model.Cancion;
import com.backend.music.Repository.CancionRepository;

@Service
public class CancionService {

    private final CancionRepository cancionRepository;

    @Autowired
    public CancionService(CancionRepository cancionRepository) {
        this.cancionRepository = cancionRepository;
    }

    public Cancion guardarCancion(String id, String albumId, String artistaId, int duracion, String genero, MultipartFile file) throws IOException {
        String titulo = file.getOriginalFilename();
        Cancion cancion = new Cancion(id, titulo, albumId, artistaId, duracion, genero, file.getBytes());
        return cancionRepository.save(cancion);
    }

    public Optional<Cancion> obtenerCancion(String id) {
        return cancionRepository.findById(id);
    }

    public byte[] transmitirCancion(String id) {
        return obtenerCancion(id)
                .map(Cancion::getArchivoAudio)
                .orElse(null);
    }
}