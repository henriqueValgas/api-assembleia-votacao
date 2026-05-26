package com.desafiojuniorassembleiavotacao.assembleiaVotacao.validation;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.service.AssociadoService;
import org.springframework.stereotype.Component;

@Deprecated
@Component
public class ValidaCpf {

    public boolean validar(String cpf) {
        if (cpf == null) return false;

        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }
}


