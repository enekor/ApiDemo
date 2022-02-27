package com.example.apidemo.controller;

import com.example.apidemo.dto.PersonaDTO;
import com.example.apidemo.mapper.PersonaMapper;
import com.example.apidemo.model.Persona;
import com.example.apidemo.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonaService repo;
    private final PersonaMapper mapper;

    @GetMapping("/personas")
    public ResponseEntity<?> selectAllPersonas(@PageableDefault(size = 10,page = 0)Pageable pageable){
        Page<Persona> personas = repo.selectAllPageable(pageable);

        if(personas.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(personas.stream().collect(Collectors.toList()));
        }
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<Persona> selectpersonaById(@PathVariable long id){
        Optional<Persona> ans = repo.selectById(id);
        if(ans.isPresent()){
            return ResponseEntity.ok(ans.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/personas")
    public ResponseEntity<Persona> insertNewPersona(@RequestBody PersonaDTO p){
        Optional<Persona> ans = repo.save(mapper.toModel(p));
        if(ans.isPresent()){
            return ResponseEntity.ok(ans.get());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/personas/{id}")
    public ResponseEntity<Persona> updatePersona(@RequestBody Persona p,@PathVariable long id){
        if(repo.existsById(id)){
            Optional<Persona> ans = repo.save(p);
            if(ans.isPresent()){
                return ResponseEntity.ok(ans.get());
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
            Optional<Persona> deleted = repo.selectById(id);
            repo.delete(id);
            if(!repo.existsById(id)){
                return ResponseEntity.badRequest().build();
            }else{
                return ResponseEntity.ok(deleted.get());
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
