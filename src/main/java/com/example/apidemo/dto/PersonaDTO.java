package com.example.apidemo.dto;

import com.example.apidemo.model.Mascota;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonaDTO {

    private long id;
    private String nombre;
    private List<Long> mascotasId = new ArrayList<>();
}
