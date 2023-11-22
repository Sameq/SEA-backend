package com.example.sea.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AdicionarAlunoDto {
	@NotBlank
	@Email
	private String email;
}
