package com.example.learningapp.domain.model.user;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * ユーザー名を表現するValue Object
 * 
 */
public class Username {
    private final String value;

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 50;

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$");

    public Username(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ユーザー名は必須です");
        }
        String trimmed = value.trim();

        if (trimmed.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("ユーザー名は" + MAX_LENGTH + "文字以上で入力してください");
        }

        if (trimmed.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("ユーザー名は" + MAX_LENGTH + "文字以内で入力してください");
        }

        if (!USERNAME_PATTERN.matcher(trimmed).matches()) {
            throw new IllegalArgumentException("ユーザー名には英数字、アンダースコア、ハイフンのみ使用できます");
        }

        this.value = value;
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
        Username username = (Username) o;
        return Objects.equals(value, username.value);
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
