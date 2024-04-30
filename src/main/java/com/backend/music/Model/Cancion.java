package com.backend.music.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "cancion")
public class Cancion {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "album_id", nullable = false)
    private String albumId;

    @Column(name = "artista_id", nullable = false)
    private String artistaId;

    @Column(name = "duracion", nullable = false)
    private int duracion;

    @Column(name = "genero")
    private String genero;

    @Lob
    @Column(name = "archivo_audio", nullable = false)
    private byte[] archivoAudio;

    public Cancion() {
    }

    public Cancion(String id, String titulo, String albumId, String artistaId, int duracion, String genero, byte[] archivoAudio) {
        this.id = id;
        this.titulo = titulo;
        this.albumId = albumId;
        this.artistaId = artistaId;
        this.duracion = duracion;
        this.genero = genero;
        this.archivoAudio = archivoAudio;
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

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(String artistaId) {
        this.artistaId = artistaId;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public byte[] getArchivoAudio() {
        return archivoAudio;
    }

    public void setArchivoAudio(byte[] archivoAudio) {
        this.archivoAudio = archivoAudio;
    }
}