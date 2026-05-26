package com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PautaRepository extends JpaRepository<Pauta, UUID> {

    List<Pauta> findAllByNome(String nome);

    Optional<Pauta> findByNome(String nome);

    boolean existsByNome(String nome);
/**
    @Query(value = "SELECT p FROM Pauta p WHERE p.dataEncerramento <= CURRENT_TIMESTAMP AND p.status = 'ABERTA' ")
    List<Pauta> buscarPautasAbertasEVencidas();
*/
}

