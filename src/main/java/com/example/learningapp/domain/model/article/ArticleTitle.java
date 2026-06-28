package com.example.learningapp.domain.model.article;

import java.util.Objects;

public class ArticleTitle {
    private final String value;

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 50;

    public ArticleTitle(String value) {
        // nullチェック
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("記事のタイトルは必須項目です");
        }

        // 前後空白の削除
        String trimmed = value.trim();

        // 文字数チェック
        if (trimmed.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("記事のタイトルは" + MIN_LENGTH + "文字以上で入力してください");
        }
        if (trimmed.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("記事のタイトルは" + MAX_LENGTH + "文字以内で入力してください");
        }

        this.value = trimmed;
    }
    
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ArticleTitle that = (ArticleTitle) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}