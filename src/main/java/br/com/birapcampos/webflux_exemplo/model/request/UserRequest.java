package br.com.birapcampos.webflux_exemplo.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @Size(min=3,max=50,message = "must be between 3 and 50 characters.")
        @NotBlank(message = "must not be null or empty.")
        String name,

        @Email(message = "Invalid email")
        @NotBlank(message = "must not be null or empty.")
        String email,

        @NotBlank(message = "must not be null or empty.")
        @Size(min=4,max=8,message = "must be between 3 and 8 characters.")
        String password) {}

