package br.com.fiap.roardemo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "roars")
public class Roar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 280)
    private String content;

    @Column(nullable = false)
    private int likes;

    @Column(nullable = false)
    private String username;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Roar() {
    }

    public Roar(Long id, String content, int likes, String username, String userId, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.likes = likes;
        this.username = username;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public Roar(Long id, String content, int likes, String username) {
        this.id = id;
        this.content = content;
        this.likes = likes;
        this.username = username;
        this.createdAt = LocalDateTime.now();
    }

    public Roar(String content, String username, String userId) {
        this.content = content;
        this.username = username;
        this.userId = userId;
        this.likes = 0;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roar roar = (Roar) o;
        return Objects.equals(id, roar.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Roar{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}