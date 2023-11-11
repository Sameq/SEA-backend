package com.example.sea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sea.model.ModelAdicionaAluno;
@Repository
public interface RepositoyAdicionarAluno extends JpaRepository<ModelAdicionaAluno, Long> {
	ModelAdicionaAluno findByEmail(String email);
}
