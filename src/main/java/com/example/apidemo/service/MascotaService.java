package com.example.apidemo.service;

import com.example.apidemo.model.Mascota;
import com.example.apidemo.repository.MascotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MascotaService {
    private final MascotaRepository repo;

    public Optional<List<Mascota>> selectAll(){
        return Optional.of(repo.findAll());
    }

    public Optional<Mascota> selectById(long id){
        return repo.findById(id);
    }

    public Page<Mascota> selectAllPageable(Pageable pageable){
        return repo.findAll(pageable);
    }

    public Optional<Mascota> save(Mascota object){
        return Optional.of(repo.save(object));
    }

    public void delete(long id){
        repo.deleteById(id);
    }

    public boolean existsById(long id) {
        return repo.existsById(id);
    }
}
