package com.desafiojuniorassembleiavotacao.assembleiaVotacao.service;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.PautaRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.PautaResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.RegistroDuplicadoException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.RegistroNaoEncontradoException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper.PautaMapper;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository.PautaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PautaService {

    private final PautaRepository repository;
    private final PautaDomainService pautaDomainService;



    public PautaService(PautaRepository repository, PautaDomainService pautaDomainService) {
        this.repository = repository;
        this.pautaDomainService = pautaDomainService;

    }

    @Transactional
    public PautaResponseDTO salvar(PautaRequestDTO request) {

        Pauta pauta = PautaMapper.toEntity(request);

        validarDuplicidadePauta(pauta);

        Pauta salva = repository.save(pauta);

        return PautaMapper.toDTO(salva);
    }

    public Pauta salvar(Pauta pauta) {
        return repository.save(pauta);
    }

    public PautaResponseDTO obterPorId(UUID id) {
        Pauta pauta = buscarPautaVotacaoPorId(id);

        return PautaMapper.toDTO(pauta);
    }

    @Transactional(readOnly = true)
    public List<PautaResponseDTO> listaPauta(String nome) {
        List<Pauta> pautas;

        if (nome == null || nome.isBlank()) {
            pautas = repository.findAll();
        } else {
            pautas = repository.findAllByNome(nome);
        }
        return pautas.stream().map(PautaMapper::toDTO).toList();

    }

    @Transactional
    public void deletar(UUID id) {

        Pauta pauta = buscarPautaVotacaoPorId(id);
        pautaDomainService.validarPodeDeletar(pauta);

        repository.delete(pauta);
    }

    public Pauta buscarPautaPorNome(String nomePauta) {
        return repository.findByNome(nomePauta).orElseThrow(() ->
                new RegistroNaoEncontradoException("Pauta não encontrada"));
    }

    public Pauta buscarPautaVotacaoPorId(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                new RegistroNaoEncontradoException("nenhuma pauta encontrada"));
    }

    public void validarDuplicidadePauta(Pauta pauta) {

        if (repository.existsByNome(pauta.getNome())) {
            throw new RegistroDuplicadoException("Pauta cadastrada");
        }
    }


}
