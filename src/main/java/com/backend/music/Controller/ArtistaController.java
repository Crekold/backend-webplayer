package com.backend.music.Controller;

import com.backend.music.Model.Artista;
import com.backend.music.Service.ArtistaService;
import com.backend.music.dto.ArtistaDto;
import com.backend.music.message.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    private final ArtistaService artistaService;

    @Autowired
    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping
    public List<Artista> obtenerTodosLosArtistas() {
        return artistaService.obtenerTodosLosArtistas();
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> crearArtista(@RequestParam("artista") String artista, @RequestParam("file") MultipartFile file) {
        try {
            Artista artistaObj = new ObjectMapper().readValue(artista, Artista.class);
            if (file != null && !file.isEmpty()) {
                artistaObj.setImagenPerfil(file.getBytes());
            }
            Artista artistaGuardado = artistaService.crearArtista(artistaObj);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Artista creado con éxito con ID: " + artistaGuardado.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("No se pudo crear el artista: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> actualizarArtista(@PathVariable String id, @RequestBody Artista artista) {
        try {
            artista.setId(id);
            Artista artistaActualizado = artistaService.actualizarArtista(artista);
            return ResponseEntity.ok(new ResponseMessage("Artista actualizado con éxito con ID: " + artistaActualizado.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("No se pudo actualizar el artista: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> eliminarArtista(@PathVariable String id) {
        try {
            artistaService.eliminarArtista(id);
            return ResponseEntity.ok(new ResponseMessage("Artista eliminado con éxito con ID: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("No se pudo eliminar el artista: " + e.getMessage()));
        }
    }
    @GetMapping("/{id}/imagen")
    public ResponseEntity<byte[]> obtenerImagenArtista(@PathVariable String id) {
        Optional<Artista> artistaOptional = artistaService.obtenerArtistaPorId(id);
        if (artistaOptional.isPresent() && artistaOptional.get().getImagenPerfil() != null) {
            Artista artista = artistaOptional.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(artista.getImagenPerfil());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/{id}")
public ResponseEntity<Object> obtenerArtistaPorId(@PathVariable String id) {
    Optional<Artista> artistaOptional = artistaService.obtenerArtistaPorId(id);
    if (artistaOptional.isPresent()) {
        Artista artista = artistaOptional.get();
        ArtistaDto artistaDto = new ArtistaDto();
        artistaDto.setId(artista.getId());
        artistaDto.setNombre(artista.getNombre());
        artistaDto.setBiografia(artista.getBiografia());
        artistaDto.setUrlImagen("/api/artistas/" + artista.getId() + "/imagen");
        return ResponseEntity.ok().body(artistaDto);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No se encontró el artista con ID: " + id));
    }
}
}