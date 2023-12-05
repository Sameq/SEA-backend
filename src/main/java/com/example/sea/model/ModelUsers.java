package com.example.sea.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelUsers implements UserDetails {
    private static final long serialVersion = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idUsers;
    @Column(nullable = false)
    private String name;
    @Email
    private String email;
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(nullable = true)
    private String codconfiguration;
    
    @ManyToMany
    @JoinTable(
        name = "user_turma",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private List<ModelTurma> turmas;
    
    public ModelUsers(String name, String email, String password, UserRole role) {
    	this.name = name;
    	this.email = email;
    	this.password = password;
    	this.role = role;
    }
    public String getEncryptedPassword() {
        return this.getEncryptedPassword();
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.PROFESSOR) {
             return Collections.singletonList( new SimpleGrantedAuthority("ROLE_PROFESSOR"));
        } else if(this.role == UserRole.ALUNO){
          return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ALUNO"));
        }else{
			return null;
		}
    }
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
