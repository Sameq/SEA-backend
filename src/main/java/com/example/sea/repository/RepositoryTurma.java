package com.example.sea.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sea.model.ModelTurma;

public interface RepositoryTurma extends JpaRepository<ModelTurma, Integer>{
	ModelTurma findByNomeTurma(String nomeTurma);

}
