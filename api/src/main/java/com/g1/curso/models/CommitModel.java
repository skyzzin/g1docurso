package com.g1.curso.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "commit_table")
public class CommitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private UUID uuid_post;
    private String sender;
    private String recived;
    @Column(length = 10000)
    private String content;
    private String createdat;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecived() {
        return recived;
    }

    public void setRecived(String recived) {
        this.recived = recived;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public UUID getUuid_post() {
        return uuid_post;
    }

    public void setUuid_post(UUID uuid_post) {
        this.uuid_post = uuid_post;
    }
}
