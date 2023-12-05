package com.example.sea.security;

import java.io.IOException;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.sea.repository.RepositoryUsers;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.var;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	@Autowired
	TokenService tokenService;
	@Autowired
	RepositoryUsers userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = this.recoverToken(request);
		if (token != null) {
			String email = tokenService.validateToken(token);
			UserDetails user = userRepository.findByEmail(email);
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
		
	}
	
	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if(authHeader ==  null) return null;
		return authHeader.replace("Bearer ", "");
		
	}
	
}
