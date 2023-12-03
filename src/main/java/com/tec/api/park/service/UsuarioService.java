package com.tec.api.park.service;

import com.tec.api.park.entity.Usuario;
import com.tec.api.park.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario updateSenha(Long id, Usuario usuario) {
        Usuario byId = this.findById(id);
        byId.setPassword(usuario.getPassword());
        return usuarioRepository.save(byId);
    }

    @Transactional
    public Usuario updateSenhaV2(Long id, String password) {
        Usuario usuario = findById(id);
        usuario.setPassword(password);
        return usuario;
    }

}
