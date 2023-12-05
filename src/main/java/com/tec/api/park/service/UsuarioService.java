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
    public Usuario updateSenhaSimples(Long id, String password) {
        Usuario usuario = findById(id);
        usuario.setPassword(password);
        return usuario;
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if(!novaSenha.equals(confirmaSenha)){
            throw new RuntimeException("Nova senha não confere com a confirmação de senha");
        }

        Usuario usuario = this.findById(id);

        if(!usuario.getPassword().equals(senhaAtual)){
            throw new RuntimeException("Senha não confere");
        }

        usuario.setPassword(novaSenha);
        return  usuario;
    }
}
