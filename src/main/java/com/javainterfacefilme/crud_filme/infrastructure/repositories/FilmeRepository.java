package com.javainterfacefilme.crud_filme.infrastructure.repositories;

import com.javainterfacefilme.crud_filme.infrastructure.entitys.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
