package com.desafiojuniorassembleiavotacao.assembleiaVotacao.client;

import com.desafiojuniorassembleiavotacao.assembleiaVotacao.controller.dto.UserInfoResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserInfoClient {

    private final RestClient restClient;

    public UserInfoClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public UserInfoResponse verificarAptidao(String cpf){
        return restClient
                .get()
                .uri("/user-info/{cpf}", cpf)
                .retrieve()
                .body(UserInfoResponse.class);
    }
}
