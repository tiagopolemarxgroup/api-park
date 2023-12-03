package com.tec.api.park.controller;

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
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario user = usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getByid(@PathVariable Long id) {
        Usuario user = usuarioService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<Usuario> updatePassword(@PathVariable Long id,@RequestBody Usuario usuario){
//        Usuario user = usuarioService.updateSenha(id, usuario);
//        return ResponseEntity.ok(user);
//    }
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> atualizarSenha(@PathVariable Long id,@RequestBody Usuario usuario){
        Usuario user = usuarioService.updateSenhaV2(id, usuario.getPassword());
        return ResponseEntity.ok(user);
    }
}
