CREATE TABLE associado(
    id BINARY(16) PRIMARY KEY,
    cpf VARCHAR(14),
    nome VARCHAR(120)
);

CREATE TABLE pauta(
    id BINARY(16) PRIMARY KEY,
    nome_pauta VARCHAR(50),
    total_votos LONG,
    total_sim LONG,
    total_nao LONG,
    resultado_pauta ENUM('APROVADA','REPROVADA','EMPATE')
);

CREATE TABLE voto(
    pauta_id BINARY(16),
    associado_id BINARY(16),
    opcao ENUM('SIM', 'NAO'),

    PRIMARY KEY(pauta_id, associado_id),

    CONSTRAINT fk_voto_pauta
        FOREIGN KEY(pauta_id)
            REFERENCES pauta(id),

    CONSTRAINT fk_voto_associado
        FOREIGN KEY (associado_id)
            REFERENCES associado(id)
);

CREATE TABLE sessao_votacao(
    id INT auto_increment,
    data_abertura DATETIME,
    data_fechamento DATETIME,
    duracao LONG,
    sessao_status ENUM('ABERTA','ENCERRADA'),
    pauta_id BINARY(16),

    PRIMARY KEY (id),
    CONSTRAINT fk_sessao_votacao_pauta
       FOREIGN KEY (pauta_id) REFERENCES pauta(id)
);