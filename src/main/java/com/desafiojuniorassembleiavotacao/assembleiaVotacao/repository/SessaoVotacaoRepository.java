package com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.SessaoStatus;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Integer> {

    Optional<SessaoVotacao> findByPautaIdAndStatus(UUID id, SessaoStatus status);

    List<SessaoVotacao> findByStatus(SessaoStatus status);

}
