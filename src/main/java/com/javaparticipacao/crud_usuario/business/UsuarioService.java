package com.javaparticipacao.crud_usuario.business;

import com.javaparticipacao.crud_usuario.infrastructure.entitys.Usuario;
import com.javaparticipacao.crud_usuario.infrastructure.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioEmail(String email){
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }

    public void deletarUsuarioEmail(String email){
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioEmail(String email, Usuario usuario){
        Usuario usuarioEntity = buscarUsuarioEmail(email);
        Usuario usuarioAtaluzado = Usuario.builder()
                .email(usuario.getEmail() != null ?
                        usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ?
                        usuario.getNome() : usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .build();
    }

    public void atualizarUsuarioId(Integer id, Usuario usuario){
        Usuario usuarioEntity = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ?
                        usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ?
                        usuario.getNome() : usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .build();

        repository.save(usuarioAtualizado);
    }
}
