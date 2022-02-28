package com.example.apidemo.controller;

import com.example.apidemo.model.Random;
import com.example.apidemo.service.RandomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RandomController {

    private final RandomService service;

    @GetMapping("/random")
    public ResponseEntity<?> buscarPorVarios(@RequestParam Optional<String> nombre, @RequestParam Optional<Integer> numero1,@PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<Random> result = service.findByArgs(nombre, numero1, pageable);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(result.stream().collect(Collectors.toList()));
        }
    }

    @PostMapping("/random")
    public ResponseEntity<?> insertRandom(@RequestBody Random r){
        Optional<Random> random = service.insertRandom(r);

        if(random.isPresent()){
            return ResponseEntity.ok(random.get());
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
}
