package com.tec.api.park.dto.mapper;

import com.tec.api.park.dto.UsuarioCreatedDto;
import com.tec.api.park.dto.UsuarioResponseDto;
import com.tec.api.park.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreatedDto createdDto) {
        return new ModelMapper().map(createdDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRoler(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> listToDto(List<Usuario> usuarios){
       return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
