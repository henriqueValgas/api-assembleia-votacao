package com.desafiojuniorassembleiavotacao.assembleiaVotacao.service;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.AssociadoJaVotouException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions.PautaJaTemVotosException;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import org.springframework.stereotype.Service;

@Service
public class PautaDomainService {

    public void validarPodeDeletar(Pauta pauta) {
        if (pauta.getTotalVotos() > 0) {
            throw new PautaJaTemVotosException("Pauta ja possui votos");
        }
    }

    public void validarDuplicidade(boolean jaExiste) {

        if (jaExiste) {
            throw new AssociadoJaVotouException("Associado ja votou");
        }
    }
}

