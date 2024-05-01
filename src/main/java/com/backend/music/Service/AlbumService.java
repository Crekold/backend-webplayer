package com.backend.music.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.backend.music.Model.Album;
import com.backend.music.Repository.AlbumRepository;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> obtenerTodosLosAlbumes() {
        return albumRepository.findAll();
    }

    public Optional<Album> obtenerAlbumPorId(String id) {
        return albumRepository.findById(id);
    }

    public Album crearAlbum(Album album) {
        return albumRepository.save(album);
    }

    public Album actualizarAlbum(Album album) {
        return albumRepository.save(album);
    }

    public void eliminarAlbum(String id) {
        albumRepository.deleteById(id);
    }
}
