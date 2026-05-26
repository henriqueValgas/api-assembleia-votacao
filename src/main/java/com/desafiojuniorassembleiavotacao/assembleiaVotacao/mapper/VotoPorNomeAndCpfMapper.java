package com.desafiojuniorassembleiavotacao.assembleiaVotacao.mapper;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoPorNomeAndCpfRequestDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.VotoPorNomeAndCpfResponseDTO;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Associado;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Pauta;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Voto;

public class VotoPorNomeAndCpfMapper {

    public static Voto toEntity(VotoPorNomeAndCpfRequestDTO dto, Pauta pauta, Associado associado) {
        Voto voto = new Voto();
        voto.setPauta(pauta);
        voto.setAssociado(associado);
        voto.setOpcao(dto.votoEnum());

        return voto;
    }

    public static VotoPorNomeAndCpfResponseDTO toDto(Voto entity) {

        return new VotoPorNomeAndCpfResponseDTO(
                entity.getPauta().getId(),
                entity.getPauta().getNome(),
                entity.getAssociado().getId(),
                entity.getAssociado().getNome(),
                entity.getAssociado().getCpf(),
                entity.getOpcao()
        );
    }
}
