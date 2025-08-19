package com.alura.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TopicCreateRequest(
        @NotBlank @Size(min = 5, max = 160) String title,
        @NotBlank @Size(min = 10, max = 5000) String message,
        @NotBlank @Size(min = 2, max = 120) String course
) {}
