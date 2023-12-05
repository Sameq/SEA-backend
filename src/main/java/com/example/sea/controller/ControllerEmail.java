package com.example.sea.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sea.dtos.AtualizarSenhaDto;
import com.example.sea.dtos.ConfirmarCodigo;
import com.example.sea.dtos.EmailDto;
import com.example.sea.dtos.RecuperaraSenhaDto;
import com.example.sea.model.ModelUsers;
import com.example.sea.repository.RepositoryUsers;
import com.example.sea.service.EmailService;

@RestController
public class ControllerEmail {
	@Autowired
	RepositoryUsers repostoryUsers; 
	@Autowired
	private  EmailService emailService;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/enviaremial")
	public ResponseEntity<?> confirmmarEmail(@RequestBody EmailDto emailDto){
		try {
			this.emailService.sendEmail(emailDto);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	} 
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/recuperarSenha")
	public ResponseEntity<?> recuperarSenhaEmail(@RequestBody RecuperaraSenhaDto recuperarSenha){
		if(this.repostoryUsers.findByEmail(recuperarSenha.getTo()) == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} 
		emailService.recuperarSenha(recuperarSenha);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/confirmarCodigo")
	public ResponseEntity<?> confirmarCodigo(@RequestBody ConfirmarCodigo confirmarCodigo){
		if (this.repostoryUsers.findByCodconfiguration(confirmarCodigo.getCodigo()) == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Código inválido");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
