package com.example.sea.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sea.dtos.LogarDto;
import com.example.sea.dtos.UsersRecordDto;
import com.example.sea.model.ModelUsers;
import com.example.sea.repository.RepositoryUsers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.experimental.var;

@RestController
public class ControllerUsers {
	@Autowired
	RepositoryUsers repostoryUsers; 
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/user")
	public ResponseEntity<?> saveUsers(@RequestBody UsersRecordDto usersRecordDto){
		ModelUsers modelUsers = new ModelUsers();
		ModelUsers emailValidacao = new ModelUsers();
		emailValidacao = repostoryUsers.findByEmail(usersRecordDto.getEmail());
		if(emailValidacao == null) {
			BeanUtils.copyProperties(usersRecordDto, modelUsers);
			return ResponseEntity.status(HttpStatus.CREATED).body(repostoryUsers.save(modelUsers));

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Email ja esxistente");
	}
	
	@GetMapping(value = "/listusers")
	public ResponseEntity<List<ModelUsers>> getAllUsers(){		
		return ResponseEntity.status(HttpStatus.OK).body(repostoryUsers.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(value="id") Integer id){
		Optional<ModelUsers> user = repostoryUsers.findById(id);
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(user.get());
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody LogarDto logar){
		ModelUsers email = repostoryUsers.findByEmail(logar.getEmail());
		if(email != null && logar.getSenha().equals(email.getSenha())){
			return ResponseEntity.status(HttpStatus.OK).body("usuario encontrado");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Senha ou Email incorretos");
	}
}
