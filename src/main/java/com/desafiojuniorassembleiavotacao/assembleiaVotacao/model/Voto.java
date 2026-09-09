package com.desafiojuniorassembleiavotacao.assembleiaVotacao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "voto")
public class Voto {

    @EmbeddedId
    private VotoId id = new VotoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pautaId")
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("associadoId")
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @Enumerated(EnumType.STRING)
    private VotoEnum opcao;

    public Voto() {
    }

    public Voto(Pauta pauta, Associado associado, String opcao) {
        this.pauta = pauta;
        this.associado = associado;
        this.opcao = VotoEnum.fromApi(opcao);

    }

    public VotoId getId() {
        return id;
    }

    public void setId(VotoId id) {
        this.id = id;
    }

    public VotoEnum getOpcao() {
        return opcao;
    }

    public void setOpcao(VotoEnum opcao) {
        this.opcao = opcao;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

}
