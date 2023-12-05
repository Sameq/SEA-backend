package com.example.sea.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.sea.model.ModelUsers;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(ModelUsers user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret); 
			String token = JWT.create()
					.withIssuer("sea-aplication")
					.withSubject(user.getEmail())
					.withClaim("role", 0)
					.withClaim("role", 1)
					.withExpiresAt(genExpirationData())
					.sign(algorithm);
			return token;
		} catch (JWTCreationException exception ) {
			throw new RuntimeException("Error while generating token", exception);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret); 
			return JWT.require(algorithm)
					.withIssuer("sea-aplication")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTCreationException exception) {
			return "";
		}
	}
	
	private Instant genExpirationData() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
