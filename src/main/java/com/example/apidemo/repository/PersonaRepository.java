package com.example.apidemo.repository;

import com.example.apidemo.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

    Page<Persona> findByNombre(String nombre, Pageable pageable);
}
