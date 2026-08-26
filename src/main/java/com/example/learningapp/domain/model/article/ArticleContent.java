package com.example.learningapp.domain.model.article;

import java.util.Objects;

public class ArticleContent {
    private final String value;

    private static final int MAX_LENGTH = 1000;

    public ArticleContent(String value) {
        if (value == null) {
            throw new IllegalArgumentException("コンテンツタイプは必須項目です");
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("本文は" + MAX_LENGTH + "文字以内で入力してください");
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value.isBlank();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ArticleContent that = (ArticleContent) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
