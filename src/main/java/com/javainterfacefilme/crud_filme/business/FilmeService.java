package com.javainterfacefilme.crud_filme.business;

import com.javainterfacefilme.crud_filme.infrastructure.entitys.Filme;
import com.javainterfacefilme.crud_filme.infrastructure.repositories.FilmeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository repository;

    public FilmeService(FilmeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Filme salvarFilme(Filme filme) {
        validarFilme(filme);
        return repository.save(filme);
    }

    public List<Filme> listarFilmes() {
        return repository.findAll();
    }

    public Filme buscarFilmePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com ID: " + id));
    }

    @Transactional
    public void excluirFilme(Long id) {
        Filme filmeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com ID: " + id));
        repository.delete(filmeExistente);
    }

    @Transactional
    public Filme atualizarFilme(Long id, Filme filmeAtualizado) {
        Filme filmeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com ID: " + id));

        validarFilme(filmeAtualizado);

        // Atualiza todos os campos
        filmeExistente.setTitulo(filmeAtualizado.getTitulo());
        filmeExistente.setDiretor(filmeAtualizado.getDiretor());
        filmeExistente.setAnoLancamento(filmeAtualizado.getAnoLancamento());
        filmeExistente.setGenero(filmeAtualizado.getGenero());

        return repository.save(filmeExistente);
    }

    private void validarFilme(Filme filme) {
        if (filme.getTitulo() == null || filme.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        if (filme.getDiretor() == null || filme.getDiretor().trim().isEmpty()) {
            throw new IllegalArgumentException("Diretor não pode ser vazio");
        }
        if (filme.getAnoLancamento() == null || filme.getAnoLancamento() < 1800) {
            throw new IllegalArgumentException("Ano de lançamento inválido");
        }
        if (filme.getGenero() == null || filme.getGenero().trim().isEmpty()) {
            throw new IllegalArgumentException("Gênero não pode ser vazio");
        }
    }
}