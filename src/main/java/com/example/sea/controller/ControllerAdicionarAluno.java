package com.example.sea.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.example.sea.dtos.AdicionarAlunoDto;
import com.example.sea.model.ModelAdicionaAluno;
import com.example.sea.model.ModelUsers;
import com.example.sea.repository.RepositoyAdicionarAluno;
import com.example.sea.repository.RepositoryUsers;

@RestController
public class ControllerAdicionarAluno {
	@Autowired
	private RepositoyAdicionarAluno repository;
	@Autowired 
	private RepositoryUsers repositoryUser;
	
	@PostMapping("/adicionarAluno")
	public ResponseEntity<?> adicionarAluno(@RequestBody AdicionarAlunoDto adicionarAluno){
			ModelUsers email = new ModelUsers();
			ModelAdicionaAluno addAluno = new ModelAdicionaAluno();
			BeanUtils.copyProperties(adicionarAluno, addAluno);
			email = repositoryUser.findByEmail(adicionarAluno.getEmail());
			if (email == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aluno nao encontrado");
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(addAluno));
	}
	
	@GetMapping(value = "/ListAlunos")
	public ResponseEntity<List<ModelAdicionaAluno>> getAllUsers(){		
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
	}
	
	@GetMapping(value = "/{email}")
	public ResponseEntity<?> buscarAluno(@PathVariable(value="email") String email){
		ModelAdicionaAluno emailAluno = new ModelAdicionaAluno();
		emailAluno = repository.findByEmail(email);
		if (email == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aluno nao encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(emailAluno);
	}
	@GetMapping(value = "/{emailDelete}")
	public ResponseEntity<?> deletarAluno(@PathVariable(value="email") String email){
		ModelAdicionaAluno emailAluno = new ModelAdicionaAluno();
		emailAluno = repository.findByEmail(email);
		if (email == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aluno nao encontrado");
		}
		repository.delete(emailAluno);
		return ResponseEntity.status(HttpStatus.OK).body("aluno deletado");
	}
	
}
