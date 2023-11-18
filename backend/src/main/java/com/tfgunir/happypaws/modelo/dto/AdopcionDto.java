package com.tfgunir.happypaws.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AdopcionDto {
    private int idAdopcion;
    private int idProtectora;
    private int idUsuario;
    private int idAnimal;
    private int estado;    
}
