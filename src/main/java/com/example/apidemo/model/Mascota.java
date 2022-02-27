package com.example.apidemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor
@Entity(name = "pet")
public class Mascota {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Persona dueño;

    private String nombre;
}
