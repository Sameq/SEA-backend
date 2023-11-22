package com.example.sea.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AlunosAdcionados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelAdicionaAluno {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Email
	private String email;
}
