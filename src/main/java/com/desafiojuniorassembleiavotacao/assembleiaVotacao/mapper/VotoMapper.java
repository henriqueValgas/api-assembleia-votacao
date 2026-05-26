package com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper;


import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Associado;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Voto;

public class VotoMapper {

    public static Voto toEntity(VotoDTO dto,
            Pauta pauta,
            Associado associado) {

        Voto voto = new Voto();
        voto.setPauta(pauta);
        voto.setAssociado(associado);
        voto.setOpcao(dto.opcao());

        return voto;
    }

    public static VotoDTO toDto(Voto entity) {
        return new VotoDTO(
                entity.getId().getPautaId(),
                entity.getId().getAssociadoId(),
                entity.getOpcao()
        );
    }
}
