package com.example.apidemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity(name = "pet")
public class Mascota {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "master")
    private Persona dueño;

    @Column(name = "name")
    private String nombre;
}
