package com.example.sea.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sea.dtos.LogarDto;
import com.example.sea.dtos.LoginResponseDto;
import com.example.sea.dtos.UsersRecordDto;
import com.example.sea.model.ModelUsers;
import com.example.sea.repository.RepositoryUsers;
import com.example.sea.security.TokenService;

import jakarta.validation.Valid;

@RestController
public class ControllerUsers {
	
	@Autowired
	RepositoryUsers repostoryUsers; 
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	TokenService tokenService;
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/user")
	public ResponseEntity<?> saveUsers(@RequestBody @Valid UsersRecordDto usersRecordDto){
		if(this.repostoryUsers.findByEmail(usersRecordDto.email()) != null) return ResponseEntity.badRequest().build();
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(usersRecordDto.password());
		ModelUsers user = new ModelUsers(usersRecordDto.name(), usersRecordDto.email(),encryptedPassword, usersRecordDto.role());
		
		this.repostoryUsers.save(user);
		
		return ResponseEntity.ok().build();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody @Valid LogarDto logar){
		var usernamePassword = new UsernamePasswordAuthenticationToken(logar.email(), logar.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        var token = tokenService.generateToken((ModelUsers)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
	}
}
