package com.tec.api.park.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioCreatedDto {
    private String username;
    private String password;
}
