package com.backend.music.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Artista")
public class Artista {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "biografia")
    private String biografia;

    @Lob
    @Column(name = "imagen_perfil")
    private byte[] imagenPerfil;

    public Artista() {
    }

    public Artista(String id, String nombre, String biografia, byte[] imagenPerfil) {
        this.id = id;
        this.nombre = nombre;
        this.biografia = biografia;
        this.imagenPerfil = imagenPerfil;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public byte[] getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(byte[] imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }
}