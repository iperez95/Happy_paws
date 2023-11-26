package com.tfgunir.happypaws.modelo.dto;

import com.tfgunir.happypaws.modelo.entities.RespuestasAdoptante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuestionarioDto {
    private int idUsuario;
    private RespuestasAdoptante[] respuestas;
}
