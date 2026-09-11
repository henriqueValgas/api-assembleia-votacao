package com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssociadoRepository extends JpaRepository<Associado, UUID> {

    List<Associado> findAllByNome(String nome);

    Optional<Associado> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

}
