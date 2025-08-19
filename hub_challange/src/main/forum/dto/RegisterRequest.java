package com.alura.forumhub.dto;

import com.alura.forumhub.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank @Size(min = 3, max = 120) String name,
        @Email @NotBlank String email,
        @NotBlank @Size(min = 6, max = 60) String password,
        @NotNull UserRole role
) {}
