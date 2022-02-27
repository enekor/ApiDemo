package com.example.apidemo.dto;

import com.example.apidemo.model.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MascotaDTO {

    private long id;
    private String nombre;
    private long dueñoId;
}
