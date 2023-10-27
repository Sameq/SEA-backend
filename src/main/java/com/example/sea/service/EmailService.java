package com.example.sea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.sea.dtos.EmailDto;
import com.example.sea.dtos.RecuperaraSenhaDto;

@Service
public class EmailService {
	@Autowired 
	private final JavaMailSender emailSend;
	
    public EmailService(JavaMailSender emailSend) {
        this.emailSend = emailSend;
    }
    public void recuperarSenha(RecuperaraSenhaDto recuperarSenha) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom(recuperarSenha.getFrom());
		email.setTo(recuperarSenha.getTo());
		email.setSubject(recuperarSenha.getTitle());
		email.setText(recuperarSenha.getText());
		emailSend.send(email);
	}
	public void sendEmail(EmailDto emailDto) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom(emailDto.getFrom());
		email.setTo(emailDto.getTo());
		email.setSubject(emailDto.getTitle());
		email.setText(emailDto.getText());
		emailSend.send(email);
	}

}
