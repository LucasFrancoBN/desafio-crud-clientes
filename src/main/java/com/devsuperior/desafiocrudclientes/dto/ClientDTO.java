package com.devsuperior.desafiocrudclientes.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ClientDTO(
    Long id,
    @NotBlank(message = "Required field")
    @Size(min = 3, message = "Name field must be at least 3 characters")
    String name,
    @NotBlank(message = "Required field")
    @CPF(message = "Invalid CPF")
    String cpf,
    @Positive(message = "Income must be have a positive value")
    Double income,
    @PastOrPresent(message = "The birthdate field cannot have a value for a future date")
    LocalDate birthDate,
    Integer children
) {
}
