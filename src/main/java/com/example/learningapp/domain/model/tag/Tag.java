package com.example.learningapp.domain.model.tag;

import java.util.Objects;

// タグを表すValue Object
public class Tag {
    private final String value;

    private static final int MAX_LENGTH = 20;

    public Tag(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("タグは空にできません");
        }
        String trimmed = value.trim();
        if (trimmed.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("タグは" + MAX_LENGTH + "文字以内で入力してください");
        }

        this.value = trimmed.toLowerCase();
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
        Tag tag = (Tag) o;
        return Objects.equals(value, tag.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public String toString() {
        return value;
    }
}