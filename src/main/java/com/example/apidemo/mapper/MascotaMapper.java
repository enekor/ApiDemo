package com.example.apidemo.mapper;

import com.example.apidemo.dto.MascotaDTO;
import com.example.apidemo.model.Mascota;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MascotaMapper {

    private final ModelMapper mapper;

    public Mascota toModel(MascotaDTO dto){
        return mapper.map(dto,Mascota.class);
    }

    public MascotaDTO toDTO(Mascota mascota){
        return mapper.map(mascota,MascotaDTO.class);
    }

    public List<Mascota> toModelList(List<MascotaDTO> lista){
        return lista.stream().map(this::toModel).collect(Collectors.toList());
    }

    public List<MascotaDTO> toDTOList(List<Mascota> lista){
        return lista.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
