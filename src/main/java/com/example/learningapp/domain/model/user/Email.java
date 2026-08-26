package com.example.learningapp.domain.model.user;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * メールアドレスを表現するValue Object
 * 
 */
public class Email {
    private final String value;

    // メールアドレスの形式を検証するための正規表現
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("メールアドレスは必須です");
        }
        String trimmed = value.trim().toLowerCase();

        if (!EMAIL_PATTERN.matcher(trimmed).matches()) {
            throw new IllegalArgumentException("メールアドレスの形式が不正です" + value);
        }

        this.value = trimmed;
    }

    /**
     * メールアドレスを取得する
     */
    public String getValue() {
        return value;
    }

    /**
     * メールアドレスのドメイン部分を取得する ドメインごとの制限を実装する際に使用する
     * 
     */
    public String getDomain() {
        int atIndex = value.indexOf('@');
        return value.substring(atIndex + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
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
