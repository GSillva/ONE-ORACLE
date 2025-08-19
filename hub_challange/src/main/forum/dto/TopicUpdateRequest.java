package com.alura.forumhub.dto;

import jakarta.validation.constraints.Size;

public record TopicUpdateRequest(
        @Size(min = 5, max = 160) String title,
        @Size(min = 10, max = 5000) String message,
        @Size(min = 2, max = 120) String course
) {}
