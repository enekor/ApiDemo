package com.example.apidemo.controller;

import com.example.apidemo.model.Mascota;
import com.example.apidemo.repository.MascotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MascotaController {

    private final MascotaRepository repo;

    @GetMapping("/mascotas")
    public ResponseEntity<List<Mascota>> selectAllMascotas(){
        List<Mascota> ans = repo.findAll();
        if(ans.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(ans);
        }
    }

    @GetMapping("/mascotas/{id}")
    public ResponseEntity<Mascota> selectMascotaById(@PathVariable long id){
        Optional<Mascota> ans = repo.findById(id);
        if(ans.isPresent()){
            return ResponseEntity.ok(ans.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/mascotas")
    public ResponseEntity<Mascota> insertNewMascota(@RequestBody Mascota p){
        Mascota ans = repo.save(p);
        if(ans !=null){
            return ResponseEntity.ok(ans);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/mascotas/{id}")
    public ResponseEntity<Mascota> updateMascota(@RequestBody Mascota p,@PathVariable long id){
        if(repo.existsById(id)){
            Mascota ans = repo.save(p);
            if(ans != null){
                return ResponseEntity.ok(ans);
            }else{
                return ResponseEntity.badRequest().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/mascotas/{id}")
    public ResponseEntity<Mascota> deleteMascota(@PathVariable long id){
        if(repo.existsById(id)){
            Mascota deleted = repo.getById(id);
            repo.deleteById(id);
            if(!repo.existsById(id)){
                return ResponseEntity.badRequest().build();
            }else{
                return ResponseEntity.ok(deleted);
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mascotas/test")
    public String test(){
        return "hola mascotas";
    }
}
