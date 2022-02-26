package com.example.apidemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity (name = "person")
public class Persona {

    @Id @GeneratedValue
    private long id;

    @Column(name = "name")
    private String nombre;

    @OneToMany(mappedBy = "dueño", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas;
}
