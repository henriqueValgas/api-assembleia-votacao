package com.desafiojuniorassembleiavotacao.assembleiaVotacao.model;

public enum VotoEnum {
    SIM,
    NAO;

    public static VotoEnum fromApi(String opcao) {
        if (opcao == null || opcao.isBlank()) {
            throw new IllegalArgumentException("Opção do voto é obrigatoria");
        }
        return switch (opcao.toUpperCase()) {
            case "SIM" -> SIM;
            case "NAO" -> NAO;
            default -> throw new IllegalArgumentException("Voto deve ser SIM ou NAO");
        };
    }

    public String toApi() {
        return switch (this) {
            case SIM -> "SIM";
            case NAO -> "NAO";
        };
    }
}
