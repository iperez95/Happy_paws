package com.tfgunir.happypaws.modelo.dto;

import com.tfgunir.happypaws.modelo.entities.Protectora;
import com.tfgunir.happypaws.modelo.entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProtectoraRequestDto {
    private Usuario usuario;
    private Protectora protectora;
}
