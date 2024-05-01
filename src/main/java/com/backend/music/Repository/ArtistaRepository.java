package com.backend.music.Repository;

import com.backend.music.Model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, String> {
}