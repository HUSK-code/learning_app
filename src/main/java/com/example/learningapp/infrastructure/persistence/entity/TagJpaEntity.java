package com.example.learningapp.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * タグテーブルに対応するJPAエンティティ
 * 
 */
@Entity
@Table(name = "tags")
@Getter
@Setter
@NoArgsConstructor
public class TagJpaEntity {

    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Column(name = "name", length = 20, nullable = false, unique = true)
    private String name;

    @Column(name = "usage_count", nullable = false)
    private int usageCount;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public TagJpaEntity(String id, String name, int usageCount, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.usageCount = usageCount;
        this.createdAt = createdAt;
    }
}
