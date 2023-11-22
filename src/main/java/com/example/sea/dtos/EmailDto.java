package com.example.sea.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
	
	private String from;

	@NotBlank
	@Email
	private String to;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String text;
}
