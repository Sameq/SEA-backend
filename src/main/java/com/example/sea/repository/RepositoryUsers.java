package com.example.sea.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sea.model.ModelUsers;

@Repository
public interface RepositoryUsers extends JpaRepository<ModelUsers, Integer>{

    @Query("SELECT i FROM ModelUsers i WHERE i.email = :email and i.senha = :senha")
    public Optional<ModelUsers> findByEmailAndSenha(String email, String senha);
}


