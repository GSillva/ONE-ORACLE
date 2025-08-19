package com.alura.forumhub.controller;

import com.alura.forumhub.dto.PageResponse;
import com.alura.forumhub.dto.TopicCreateRequest;
import com.alura.forumhub.dto.TopicResponse;
import com.alura.forumhub.dto.TopicUpdateRequest;
import com.alura.forumhub.model.User;
import com.alura.forumhub.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<TopicResponse> create(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody TopicCreateRequest request
    ) {
        return ResponseEntity.ok(topicService.create(request, user));
    }

    @GetMapping
    public ResponseEntity<PageResponse<TopicResponse>> list(Pageable pageable) {
        Page<TopicResponse> page = topicService.list(pageable);
        PageResponse<TopicResponse> response = new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody TopicUpdateRequest request
    ) {
        return ResponseEntity.ok(topicService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
