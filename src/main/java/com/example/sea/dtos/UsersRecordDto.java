package com.example.sea.dtos;

import com.example.sea.model.UserRole;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record UsersRecordDto(String name,String email,String password, UserRole role) {
	
}


