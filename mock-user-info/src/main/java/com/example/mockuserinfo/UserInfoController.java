package com.example.mockuserinfo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    @GetMapping("/{cpf}")
    public UserInfoResponse verificarAptidao(@PathVariable String cpf) {
        char ultimoDigito = cpf.charAt(cpf.length() - 1);

        boolean ableToVote = ultimoDigito != '1'
                && ultimoDigito != '2'
                && ultimoDigito != '7';

        return new UserInfoResponse(ableToVote);
    }
}
