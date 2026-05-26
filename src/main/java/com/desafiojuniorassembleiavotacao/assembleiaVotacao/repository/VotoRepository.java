package com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Associado;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Voto;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.VotoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface VotoRepository extends JpaRepository<Voto, VotoId> {

    boolean existsByPautaAndAssociado(Pauta p, Associado a);

    @Query("SELECT COUNT(v) FROM Voto v WHERE v.pauta.id = :pautaId AND v.opcao = 'SIM'")
    long contaVotosSim (UUID pautaId);

    @Query("SELECT COUNT(v) FROM Voto v WHERE v.pauta.id = :pautaId AND v.opcao = 'NAO'")
    long contaVotosNao(UUID pautaId);

}
