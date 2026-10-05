package com.desafiojuniorassembleiavotacao.assembleiaVotacao.service;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.model.Associado;
import com.desafiojuniorassembleiavotacao.assembleiaVotacao.repository.AssociadoRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class AssociadoDetailsService implements UserDetailsService {

    private AssociadoRepository associadoRepository;

    public AssociadoDetailsService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException{

        Associado associado = associadoRepository.findByCpf(cpf).
                orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));

        return User.builder()
                .username(associado.getCpf())
                .password(associado.getSenha())
                .roles("ASSOCIADO").build();
    }
}