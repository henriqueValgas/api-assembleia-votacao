package com.desafiojuniorassembleiavotacao.assembleiaVotacao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @org.hibernate.annotations.JdbcTypeCode(Types.BINARY)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "nome_pauta", length = 50, nullable = false)
    private String nome;

    @OneToOne(mappedBy = "pauta")
    private SessaoVotacao sessaoVotacao;

    @Column(name = "total_votos")
    private long totalVotos;

    @Column(name = "total_sim")
    private long totalSim;

    @Column(name = "total_nao")
    private long totalNao;

    @Enumerated(EnumType.STRING)
    @Column(name = "resultado_pauta")
    private ResultadoPauta resultado;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private final List<Voto> votos = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomePauta) {
        this.nome = nomePauta;
    }

    public SessaoVotacao getSessaoVotacao() {
        return sessaoVotacao;
    }

    public void setSessaoVotacao(SessaoVotacao sessaoVotacao) {
        this.sessaoVotacao = sessaoVotacao;
    }

    public long getTotalSim() {
        return totalSim;
    }

    public void setTotalSim(long totalSim) {
        this.totalSim = totalSim;
    }

    public long getTotalNao() {
        return totalNao;
    }

    public void setTotalNao(long totalNao) {
        this.totalNao = totalNao;
    }

    public long getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(long totalVotos) {
        this.totalVotos = totalVotos;
    }

    public ResultadoPauta getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoPauta resultado) {
        this.resultado = resultado;
    }

    public List<Voto> getVotos() {
        return votos;
    }

}
