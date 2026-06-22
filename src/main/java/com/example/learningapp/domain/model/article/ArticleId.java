package com.example.learningapp.domain.model.article;

import java.util.Objects;
import java.util.UUID;

// 記事を一意に識別するためのIDを表現する Value Object
public class ArticleId {
    private final String value;

    public ArticleId(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("記事Idは必須です");
        }

        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("記事IDの形式が不正です：" + value);
        }
        this.value = value;
    }

    // 新しい記事IDを生成する静的ファクトリメソッド
    public static ArticleId generateNewId() {
        return new ArticleId(UUID.randomUUID().toString());
    }

    // 記事IDを取得するためのメソッド
    public String getValue() {
        return value;
    }

    // 値による等価性を担保するためのメソッド
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ArticleId articleId = (ArticleId) o;
        return Objects.equals(value, articleId.value);
    }

    // ハッシュ化メソッド
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
