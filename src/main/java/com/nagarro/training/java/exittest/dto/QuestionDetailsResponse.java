package com.nagarro.training.java.exittest.dto;

import com.nagarro.training.java.exittest.enums.QuestionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionDetailsResponse {

    private Long id;

    private String subject;

    private String body;

    private String tags;

    private String product;

    private UserResponse user;

    private LocalDateTime createdOn;

    private QuestionStatus status;

    private List<ReplyResponse> replies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public QuestionStatus getStatus() {
        return status;
    }

    public void setStatus(QuestionStatus status) {
        this.status = status;
    }

    public List<ReplyResponse> getReplies() {
        return replies;
    }

    public void setReplies(List<ReplyResponse> replies) {
        this.replies = replies;
    }
}
