package com.desafiojuniorassembleiavotacao.assembleiaVotacao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(
            @Value("${user-info.url}") String userInfoUrl
    ) {
        return RestClient
                .builder()
                .baseUrl(userInfoUrl)
                .build();
    }
}
