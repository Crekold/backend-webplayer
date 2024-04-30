package com.backend.music.Repository;

import com.backend.music.Model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepository extends JpaRepository<Cancion, String> {
}