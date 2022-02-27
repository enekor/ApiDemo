package com.example.apidemo.mapper;

import com.example.apidemo.dto.PersonaDTO;
import com.example.apidemo.model.Persona;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PersonaMapper {
    private final ModelMapper mapper;

    public Persona toModel(PersonaDTO dto){
        return mapper.map(dto,Persona.class);
    }

    public PersonaDTO toDTO(Persona Persona){
        return mapper.map(Persona,PersonaDTO.class);
    }

    public List<Persona> toModelList(List<PersonaDTO> lista){
        return lista.stream().map(this::toModel).collect(Collectors.toList());
    }

    public List<PersonaDTO> toDTOList(List<Persona> lista){
        return lista.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
