package com.tec.api.park.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioResponseDto {
    private Long id;
    private String username;
    private String roler;

}
