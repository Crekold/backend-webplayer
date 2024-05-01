package com.backend.music.Controller;


import com.backend.music.Model.Cancion;
import com.backend.music.Service.CancionService;
import com.backend.music.dto.CancionDto;
import com.backend.music.message.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    private final CancionService cancionService;

    @Autowired
    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @PostMapping("/subir")
    public ResponseEntity<ResponseMessage> subirCancion(
            @RequestParam("id") String id,
            @RequestParam("albumId") String albumId,
            @RequestParam("artistaId") String artistaId,
            @RequestParam("duracion") int duracion,
            @RequestParam("genero") String genero,
            @RequestParam("file") MultipartFile file) {
        try {
            Cancion cancion = cancionService.guardarCancion(id, albumId, artistaId, duracion, genero, file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Canción subida con éxito con ID: " + cancion.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("No se pudo subir la canción: " + e.getMessage()));
        }
    }
  @GetMapping("/{id}")
public ResponseEntity<Object> obtenerCancion(@PathVariable String id) {
    Optional<Cancion> cancionOptional = cancionService.obtenerCancion(id);
    if (cancionOptional.isPresent()) {
        Cancion cancion = cancionOptional.get();
        CancionDto cancionDto = new CancionDto();
        cancionDto.setId(cancion.getId());
        cancionDto.setAlbumId(cancion.getAlbumId());
        cancionDto.setArtistaId(cancion.getArtistaId());
        cancionDto.setDuracion(cancion.getDuracion());
        cancionDto.setGenero(cancion.getGenero());
        cancionDto.setUrlCancion("/api/canciones/stream/" + cancion.getId());
        return ResponseEntity.ok().body(cancionDto);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("No se encontró la canción con ID: " + id));
    }
}

    @GetMapping("/stream/{id}")
    public ResponseEntity<ByteArrayResource> transmitirCancion(@PathVariable String id) {
        byte[] data = cancionService.transmitirCancion(id);
        if (data != null) {
            ByteArrayResource resource = new ByteArrayResource(data);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                    .contentType(MediaType.parseMediaType("audio/mpeg"))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}