package com.example.sea.controller;

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
import com.example.sea.repository.RepostoryUsers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.experimental.var;

@RestController
//	@RequestMapping(value = "/user", produces = MediaType.TEXT_PLAIN_VALUE,  method = RequestMethod.POST )
public class ControllerUsers {
	@Autowired
	RepostoryUsers repostoryUsers; 
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/user")
	public ResponseEntity<ModelUsers> saveUsers(@RequestBody UsersRecordDto usersRecordDto){
		System.out.println("teste");
		ModelUsers modelUsers = new ModelUsers();
		BeanUtils.copyProperties(usersRecordDto, modelUsers);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repostoryUsers.save(modelUsers));
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
	public ResponseEntity<?> longar(@RequestBody LogarDto logar){
		System.out.println("entrou");
		ModelUsers email = repostoryUsers.findByEmail(logar.getEmail());
		ModelUsers senha = repostoryUsers.findBySenha(logar.getSenha());
		if(email == null && senha == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Senha ou Email incorretos");
		}
		return ResponseEntity.status(HttpStatus.OK).body("usuario encontrado");
	}
}
