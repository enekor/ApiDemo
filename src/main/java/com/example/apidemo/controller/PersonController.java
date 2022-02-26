package com.example.apidemo.controller;

import com.example.apidemo.model.Persona;
import com.example.apidemo.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class PersonController {

    private final PersonaRepository repo;

    @GetMapping("/personas")
    public ResponseEntity<List<Persona>> selectAllPersonas(){
        List<Persona> ans = repo.findAll();
        if(ans.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(ans);
        }
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<Persona> selectpersonaById(@PathVariable long id){
        Optional<Persona> ans = repo.findById(id);
        if(ans.isPresent()){
            return ResponseEntity.ok(ans.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/personas")
    public ResponseEntity<Persona> insertNewPersona(@RequestBody Persona p){
        Persona ans = repo.save(p);
        if(ans !=null){
            return ResponseEntity.ok(ans);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/personas/{id}")
    public ResponseEntity<Persona> updatePersona(@RequestBody Persona p,@PathVariable long id){
        if(repo.existsById(id)){
            Persona ans = repo.save(p);
            if(ans != null){
                return ResponseEntity.ok(ans);
            }else{
                return ResponseEntity.badRequest().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/personas/{id}")
    public ResponseEntity<Persona> deletePersona(@PathVariable long id){
        if(repo.existsById(id)){
            Persona deleted = repo.getById(id);
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

    @GetMapping("/personas/test")
    public String test(){
        return "hola personas";
    }
}
