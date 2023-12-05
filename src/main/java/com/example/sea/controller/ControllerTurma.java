package com.example.sea.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.sea.dtos.BuscarTurmaDto;
import com.example.sea.dtos.TurmaDto;
import com.example.sea.model.ModelTurma;
import com.example.sea.model.ModelUsers;
import com.example.sea.repository.RepositoryTurma;
import com.example.sea.repository.RepositoryUsers;

import jakarta.validation.Valid;

@Controller
public class ControllerTurma {
	
	@Autowired
	RepositoryTurma repositoryTurma;
	@Autowired
	RepositoryUsers repositoryUsers;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/criarturma")
	public ResponseEntity<?> criarTurma(@RequestBody @Valid TurmaDto turmaDto){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		ModelUsers professor = this.repositoryUsers.findByEmail(username);
		if(professor == null) return ResponseEntity.badRequest().body("Professor nao encontrado");
		
		if(this.repositoryUsers.findByEmail(turmaDto.emailAluno()) == null) return ResponseEntity.badRequest().body("Aluno Nao encontrado");
		
		ModelTurma turma = new ModelTurma( turmaDto.emailAluno(), turmaDto.nomeTurma(), professor.getIdUsers());
		
		this.repositoryTurma.save(turma);
		
		return ResponseEntity.ok().build();
	}
	
//	@CrossOrigin(origins = "*", allowedHeaders = "*")
//	@GetMapping(value = "/{buscarturma}")
//	public ResponseEntity<?> buscarTurma(@PathVariable("buscarturma") String buscarturma){
//		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		UserDetails userDatails = (UserDetails) authentication.getPrincipal();
//		ModelUsers user = this.repositoryUsers.findByEmail(userDatails.getUsername());
//		Optional<ModelTurma> turma = this.repositoryTurma.findById(user.getIdUsers());
//		
//		if(turma == null) return ResponseEntity.badRequest().body("deu ruim");
//		return ResponseEntity.ok().body(turma);
//	}
}
