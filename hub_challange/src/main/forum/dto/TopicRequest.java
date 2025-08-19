package com.alura.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TopicRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String message;

    @NotBlank
    private String course;
}
