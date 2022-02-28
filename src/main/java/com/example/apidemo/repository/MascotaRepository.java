package com.example.apidemo.repository;

import com.example.apidemo.model.Mascota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota,Long> {
    Page<Mascota> findByNombre(String nombre, Pageable pageable);
}
