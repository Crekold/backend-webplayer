package com.backend.music.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "artista_id", nullable = false)
    private String artistaId;

    @Column(name = "ano_lanzamiento", nullable = false)
    private Integer anoLanzamiento;

    @Column(name = "genero")
    private String genero;

    @Lob
    @Column(name = "imagen_portada")
    private byte[] imagenPortada;

    // Getters y setters

    public Album() {
    }

    public Album(String id, String titulo, String artistaId, Integer anoLanzamiento, String genero, byte[] imagenPortada) {
        this.id = id;
        this.titulo = titulo;
        this.artistaId = artistaId;
        this.anoLanzamiento = anoLanzamiento;
        this.genero = genero;
        this.imagenPortada = imagenPortada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }   

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(String artistaId) {
        this.artistaId = artistaId;
    }

    public Integer getAnoLanzamiento() {
        return anoLanzamiento;
    }

    public void setAnoLanzamiento(Integer anoLanzamiento) {
        this.anoLanzamiento = anoLanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public byte[] getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(byte[] imagenPortada) {
        this.imagenPortada = imagenPortada;
    }
}