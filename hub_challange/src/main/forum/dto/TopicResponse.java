package com.alura.forumhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopicResponse {
    private Long id;
    private String title;
    private String message;
    private String course;
    private String author;
}
