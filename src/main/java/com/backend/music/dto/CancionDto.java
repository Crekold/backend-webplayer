package com.backend.music.dto;


public class CancionDto {
    private String id;
    private String titulo;
    private AlbumDto album;
    private String artistaId;
    private Integer duracion;
    private String genero;
    private String urlCancion;

    // Getters y setters

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
    public AlbumDto getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDto album) {
        this.album = album;
    }

    public String getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(String artistaId) {
        this.artistaId = artistaId;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getUrlCancion() {
        return urlCancion;
    }

    public void setUrlCancion(String urlCancion) {
        this.urlCancion = urlCancion;
    }
}