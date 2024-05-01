package com.backend.music.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.backend.music.Model.Artista;
import com.backend.music.Repository.ArtistaRepository;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    public List<Artista> obtenerTodosLosArtistas() {
        return artistaRepository.findAll();
    }

    public Optional<Artista> obtenerArtistaPorId(String id) {
        return artistaRepository.findById(id);
    }

    public Artista crearArtista(Artista artista) {
        return artistaRepository.save(artista);
    }

    public Artista actualizarArtista(Artista artista) {
        return artistaRepository.save(artista);
    }

    public void eliminarArtista(String id) {
        artistaRepository.deleteById(id);
    }
}
