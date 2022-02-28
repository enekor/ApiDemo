package com.example.apidemo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data @NoArgsConstructor
@Entity
public class Random {

    @Id @GeneratedValue
    private long id;

    private String nombre;
    private int numero1 = (int)((Math.random()*10)+1);
    private int numero2= (int)((Math.random()*10)+1);

}
