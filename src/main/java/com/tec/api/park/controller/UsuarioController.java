package com.tec.api.park.controller;

import com.tec.api.park.dto.UsuarioCreatedDto;
import com.tec.api.park.dto.UsuarioResponseDto;
import com.tec.api.park.dto.UsuarioSenhaDto;
import com.tec.api.park.dto.mapper.UsuarioMapper;
import com.tec.api.park.entity.Usuario;
import com.tec.api.park.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioCreatedDto dto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getByid(@PathVariable Long id) {
        Usuario user = usuarioService.findById(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> findAll() {
        List<Usuario> list = usuarioService.findAll();
        return ResponseEntity.ok(UsuarioMapper.listToDto(list));
    }

    //    @PatchMapping("/{id}")
//    public ResponseEntity<Usuario> updatePassword(@PathVariable Long id,@RequestBody Usuario usuario){
//        Usuario user = usuarioService.updateSenha(id, usuario);
//        return ResponseEntity.ok(user);
//    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarSenha(@PathVariable Long id, @RequestBody UsuarioSenhaDto dto) {
        Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }
}
