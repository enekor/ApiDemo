package com.example.apidemo.service;

import com.example.apidemo.model.Persona;
import com.example.apidemo.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaService{
    private final PersonaRepository repo;

    public Optional<List<Persona>> selectAll(){
        return Optional.of(repo.findAll());
    }

    public Optional<Persona> selectById(long id){
        return repo.findById(id);
    }

    public Page<Persona> selectAllPageable(Pageable pageable){
        return repo.findAll(pageable);
    }

    public Optional<Persona> save(Persona object){
        return Optional.of(repo.save(object));
    }
    public void delete(long id){
        repo.deleteById(id);
    }

    public boolean existsById(long id) {
        return repo.existsById(id);
    }

    public Page<Persona> findByNombre(String nombre, Pageable pageable){
        return repo.findByNombre(nombre,pageable);
    }
}
