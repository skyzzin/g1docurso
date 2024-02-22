package com.g1.curso.models;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "chat_table")
@Entity
public class GlobalChatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID user_post;
    private String username;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUser_post() {
        return user_post;
    }

    public void setUser_post(UUID user_post) {
        this.user_post = user_post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
