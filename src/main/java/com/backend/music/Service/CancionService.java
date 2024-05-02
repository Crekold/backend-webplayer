package com.backend.music.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import com.backend.music.Model.Album;
import com.backend.music.Model.Cancion;
import com.backend.music.Repository.CancionRepository;
import com.backend.music.dto.AlbumDto;
import com.backend.music.dto.CancionDto;

@Service
public class CancionService {

    private final CancionRepository cancionRepository;
    private final AlbumService albumService;

    @Autowired
    public CancionService(CancionRepository cancionRepository, AlbumService albumService) {
        this.cancionRepository = cancionRepository;
        this.albumService = albumService;
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

    public CancionDto obtenerCancionDto(String id) {
        Optional<Cancion> cancionOptional = obtenerCancion(id);
        if (cancionOptional.isPresent()) {
            Cancion cancion = cancionOptional.get();
            Optional<Album> albumOptional = albumService.obtenerAlbumPorId(cancion.getAlbumId());
            if (albumOptional.isPresent()) {
                Album album = albumOptional.get();
                AlbumDto albumDto = new AlbumDto();
                albumDto.setId(album.getId());
                albumDto.setTitulo(album.getTitulo());
                albumDto.setArtistaId(album.getArtistaId());
                albumDto.setAnoLanzamiento(album.getAnoLanzamiento());
                albumDto.setGenero(album.getGenero());
                albumDto.setUrlImagenPortada("/api/albumes/" + album.getId() + "/imagen");
    
                CancionDto cancionDto = new CancionDto();
                cancionDto.setId(cancion.getId());
                cancionDto.setTitulo(cancion.getTitulo()); // Agrega esta línea
                cancionDto.setAlbum(albumDto);
                cancionDto.setArtistaId(cancion.getArtistaId());
                cancionDto.setDuracion(cancion.getDuracion());
                cancionDto.setGenero(cancion.getGenero());
                cancionDto.setUrlCancion("/api/canciones/stream/" + cancion.getId());
                return cancionDto;
            }
        }
        return null;
    }
}