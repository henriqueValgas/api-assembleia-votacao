package com.desafiojuniorassembleiavotacao.assembleiaVotacao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.sql.Types;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class VotoId implements Serializable {

    @org.hibernate.annotations.JdbcTypeCode(Types.BINARY)
    @Column(name = "pauta_id", columnDefinition = "BINARY(16)")
    private UUID pautaId;

    @org.hibernate.annotations.JdbcTypeCode(Types.BINARY)
    @Column(name = "associado_id", columnDefinition = "BINARY(16)")
    private UUID associadoId;

    public VotoId() {
    }

    public VotoId(UUID pautaId, UUID associadoId) {
        this.pautaId = pautaId;
        this.associadoId = associadoId;
    }

    public UUID getPautaId() {
        return pautaId;
    }

    public void setPautaId(UUID pautaId) {
        this.pautaId = pautaId;
    }

    public UUID getAssociadoId() {
        return associadoId;
    }

    public void setAssociadoId(UUID associadoId) {
        this.associadoId = associadoId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof VotoId that)) return false;
        return Objects.equals(pautaId, that.pautaId) &&
                Objects.equals(associadoId, that.associadoId);

    }

    public int hashCode() {
        return Objects.hash(pautaId, associadoId);
    }
}
