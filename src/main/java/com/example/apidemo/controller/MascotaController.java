package com.example.apidemo.controller;

//import com.example.apidemo.mapper.MascotaMapper;
import com.example.apidemo.model.Mascota;
import com.example.apidemo.service.MascotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MascotaController {

    private final MascotaService repo;
//    private final MascotaMapper mapper;

    @GetMapping("/mascotas")
    public ResponseEntity<List<Mascota>> selectAllMascotas(@PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<Mascota> mascotas = repo.selectAllPageable(pageable);

        if(mascotas.isEmpty()){
            return  ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(mascotas.stream().collect(Collectors.toList()));
        }
    }

    @GetMapping("/mascotas/{id}")
    public ResponseEntity<Mascota> selectMascotaById(@PathVariable long id){
        Optional<Mascota> ans = repo.selectById(id);
        return ans.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/mascotas", params = "nombre")
    public ResponseEntity<?> findByNombre(String nombre, Pageable pageable){
        Page<Mascota> mascotas = repo.findByNombre(nombre, pageable);

        if(mascotas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(mascotas.stream().collect(Collectors.toList()));
        }
    }

    @PostMapping("/mascotas")
    public ResponseEntity<Mascota> insertNewMascota(@RequestBody Mascota p){
        Optional<Mascota> ans = repo.save(p);
        return ans.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/mascotas/{id}")
    public ResponseEntity<Mascota> updateMascota(@RequestBody Mascota p,@PathVariable long id){
        if(repo.existsById(id)){
            Optional<Mascota> ans = repo.save(p);
            return ans.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/mascotas/{id}")
    public ResponseEntity<Mascota> deleteMascota(@PathVariable long id){
        if(repo.existsById(id)){
            Mascota deleted = repo.selectById(id).get();
            repo.delete(id);
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
