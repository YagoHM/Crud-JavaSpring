package com.javaparticipacao.crud_usuario.controller;

import com.javaparticipacao.crud_usuario.business.UsuarioService;
import com.javaparticipacao.crud_usuario.infrastructure.entitys.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario) {
        usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Usuario> buscarUsuarioEmail(@RequestParam String email) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuarioEmail(@RequestParam String email) {
        usuarioService.deletarUsuarioEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarUsuarioId(@RequestParam Integer id, @RequestBody Usuario usuario) {
        usuarioService.atualizarUsuarioId(id, usuario);
        return ResponseEntity.ok().build();
    }
}
