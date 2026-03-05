package com.example.domain.model.article;

import java.util.Objects;
import java.util.UUID;

public final class ArticleId {
    private final String value;

    private ArticleId(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ArticleIdは空にできません。");
        }
        this.value = value;
    }

    public static ArticleId newId() {
        return new ArticleId(UUID.randomUUID().toString());
    }

    public static ArticleId reconstruct(String value) {
        return new ArticleId(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleId articleId = (articleId) o;
        return Object.equals(value, articleId.value);
    }

    @Override
    public int hashCode() {
        return Object.hash(value);
    }

    @Override
    public String toString() {
        return "ArticleId{value='" + value + "'}";
    }
}