package com.desafiojuniorassembleiavotacao.assembleiaVotacao.exceptions;

public class SessaoFechadaException extends RuntimeException {
    public SessaoFechadaException(String massege){
        super(massege);
    }
}
