package com.backend.music.Controller;

import com.backend.music.Model.Album;
import com.backend.music.Service.AlbumService;
import com.backend.music.dto.AlbumDto;
import com.backend.music.message.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/albumes")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public List<Album> obtenerTodosLosAlbumes() {
        return albumService.obtenerTodosLosAlbumes();
    }

    @GetMapping("/{id}")
public ResponseEntity<Object> obtenerAlbumPorId(@PathVariable String id) {
    Optional<Album> albumOptional = albumService.obtenerAlbumPorId(id);
    if (albumOptional.isPresent()) {
        Album album = albumOptional.get();
        AlbumDto albumDto = new AlbumDto();
        albumDto.setId(album.getId());
        albumDto.setTitulo(album.getTitulo());
        albumDto.setArtistaId(album.getArtistaId());
        albumDto.setAnoLanzamiento(album.getAnoLanzamiento());
        albumDto.setGenero(album.getGenero());
        albumDto.setUrlImagenPortada("/api/albumes/" + album.getId() + "/imagen");
        return ResponseEntity.ok().body(albumDto);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No se encontró el álbum con ID: " + id));
    }
}

@PostMapping
public ResponseEntity<ResponseMessage> crearAlbum(@RequestParam("album") String album, @RequestParam("file") MultipartFile file) {
    try {
        Album albumObj = new ObjectMapper().readValue(album, Album.class);
        if (file != null && !file.isEmpty()) {
            albumObj.setImagenPortada(file.getBytes());
        }
        Album albumGuardado = albumService.crearAlbum(albumObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Álbum creado con éxito con ID: " + albumGuardado.getId()));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("No se pudo crear el álbum: " + e.getMessage()));
    }
}

    @PutMapping("/{id}")
    public Album actualizarAlbum(@PathVariable String id, @RequestBody Album album) {
        album.setId(id);
        return albumService.actualizarAlbum(album);
    }

    @DeleteMapping("/{id}")
    public void eliminarAlbum(@PathVariable String id) {
        albumService.eliminarAlbum(id);
    }
    @GetMapping("/{id}/imagen")
public ResponseEntity<byte[]> obtenerImagenPortada(@PathVariable String id) {
    Optional<Album> albumOptional = albumService.obtenerAlbumPorId(id);
    if (albumOptional.isPresent() && albumOptional.get().getImagenPortada() != null) {
        Album album = albumOptional.get();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(album.getImagenPortada());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
}