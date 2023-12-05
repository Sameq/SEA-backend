package com.example.sea.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="Turma")
@Getter
@Setter
public class ModelTurma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String email;
	private String nomeTurma;
	private Integer professor_id;
	
	@JoinColumn(name = "professor_id")
	private ModelUsers professor;

	public ModelTurma( String email, String nomeTurma, Integer professor_id) {
		this.email = email;
		this.nomeTurma = nomeTurma;
		this.professor_id = professor_id;
	}
	
	
}
