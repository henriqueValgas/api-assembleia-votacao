package com.desafiojuniorassembleiavotacao.assembleiaVotacao.service;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.AssociadoRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.AssociadoResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.RegistroDuplicadoException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.RegistroNaoEncontradoException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper.AssociadoMapper;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Associado;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository.AssociadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssociadoService {

    private final AssociadoRepository repository;

    public AssociadoService(AssociadoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AssociadoResponseDTO salvar(AssociadoRequestDTO dto) {

        Associado associado = AssociadoMapper.toEntity(dto);

        if (repository.existsByCpf(dto.cpf())) {
            throw new RegistroDuplicadoException("Usuario ja cadastrado com esse CPF");
        }

        Associado associadoSalvo = repository.save(associado);

        return AssociadoMapper.toDto(associadoSalvo);
    }

    @Transactional
    public AssociadoResponseDTO atualizar(UUID id, AssociadoRequestDTO dto) {

        Associado associado = buscarAssociado(id);

        Optional<Associado> associadoCpf = repository.findByCpf(dto.cpf());

        if (associadoCpf.isPresent() && !associadoCpf.get().getId().equals(id)) {
            throw new RegistroDuplicadoException("Cpf ja cadastrado");
        }
        AssociadoMapper.updateAssociado(dto, associado);

        return AssociadoMapper.toDto(associado);
    }

    @Transactional(readOnly = true)
    public AssociadoResponseDTO obterPorId(UUID id) {

        Associado associado = buscarAssociado(id);

        return AssociadoMapper.toDto(associado);
    }

    @Transactional
    public void deletar(UUID id) {

        Associado associado = buscarAssociado(id);

        repository.delete(associado);
    }

    @Transactional(readOnly = true)
    public List<AssociadoResponseDTO> listarTodos(String nome) {
        List<Associado> associados;

        if (nome == null || nome.isBlank()) {
            associados = repository.findAll();
        } else {
            associados = repository.findAllByNome(nome);
        }
        return associados.stream().map(AssociadoMapper::toDto).toList();
    }

    @Transactional
    public Associado buscarAssociadoPorCpf(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(() ->
                new RegistroNaoEncontradoException("Associado nao encontrado"));
    }

    @Transactional
    public Associado buscarAssociado(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                new RegistroNaoEncontradoException("Associado nao encontrado"));
    }
}
