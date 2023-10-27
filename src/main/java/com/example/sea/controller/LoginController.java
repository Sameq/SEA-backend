package com.example.sea.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sea.dtos.LogarDto;
import com.example.sea.model.ModelUsers;
import com.example.sea.repository.RepositoryUsers;

@RestController
@RequestMapping("/user")
public class LoginController {
    private final RepositoryUsers repositoryUsers;

    @Autowired
    public LoginController(RepositoryUsers repositoryUsers) {
        this.repositoryUsers = repositoryUsers;
    }

//    @PostMapping("/login") 
    public ResponseEntity<?> findByEmailAndSenha(@RequestBody LogarDto logarDto) {
        String email = logarDto.getEmail();
        String senha = logarDto.getSenha();
        System.out.println("entrou");
        Optional<ModelUsers> user = repositoryUsers.findByEmailAndSenha(email, senha);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }
}

