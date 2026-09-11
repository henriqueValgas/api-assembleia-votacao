package com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PautaRepository extends JpaRepository<Pauta, UUID> {

    List<Pauta> findAllByNome(String nome);

    Optional<Pauta> findByNome(String nome);

    boolean existsByNome(String nome);

}

