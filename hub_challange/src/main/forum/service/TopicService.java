package com.alura.forumhub.service;

import com.alura.forumhub.dto.TopicRequest;
import com.alura.forumhub.dto.TopicResponse;
import com.alura.forumhub.exception.ResourceNotFoundException;
import com.alura.forumhub.model.Topic;
import com.alura.forumhub.model.User;
import com.alura.forumhub.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public TopicResponse create(TopicRequest request, User user) {
        Topic topic = Topic.builder()
                .title(request.getTitle())
                .message(request.getMessage())
                .course(request.getCourse())
                .author(user)
                .build();
        repository.save(topic);
        return toResponse(topic);
    }

    public Page<TopicResponse> list(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public TopicResponse get(Long id) {
        Topic topic = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
        return toResponse(topic);
    }

    public TopicResponse update(Long id, TopicRequest request) {
        Topic topic = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
        topic.setTitle(request.getTitle());
        topic.setMessage(request.getMessage());
        topic.setCourse(request.getCourse());
        repository.save(topic);
        return toResponse(topic);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Topic not found");
        }
        repository.deleteById(id);
    }

    private TopicResponse toResponse(Topic topic) {
        return new TopicResponse(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCourse(),
                topic.getAuthor().getName()
        );
    }
}
