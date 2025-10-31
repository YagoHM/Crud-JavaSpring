package com.javainterfacefilme.crud_filme.controller;

import com.javainterfacefilme.crud_filme.business.FilmeService;
import com.javainterfacefilme.crud_filme.infrastructure.entitys.Filme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filme")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FilmeController {

    private final FilmeService filmeService;

    @PostMapping
    public ResponseEntity<Filme> salvarFilme(@RequestBody Filme filme) {
        try {
            Filme filmeSalvo = filmeService.salvarFilme(filme);
            return ResponseEntity.status(HttpStatus.CREATED).body(filmeSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listarFilmes() {
        try {
            List<Filme> filmes = filmeService.listarFilmes();
            return ResponseEntity.ok(filmes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarFilmePorId(@PathVariable Long id) {
        try {
            Filme filme = filmeService.buscarFilmePorId(id);
            return ResponseEntity.ok(filme);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody Filme filme) {
        try {
            Filme filmeAtualizado = filmeService.atualizarFilme(id, filme);
            return ResponseEntity.ok(filmeAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFilme(@PathVariable Long id) {
        try {
            filmeService.excluirFilme(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}