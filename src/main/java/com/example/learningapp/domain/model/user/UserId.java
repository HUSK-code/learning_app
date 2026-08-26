package com.example.learningapp.domain.model.user;

import java.util.Objects;
import java.util.UUID;

/** 
 * ユーザーIDを表現するValue Object
 * 
 */
public class UserId {
    private final String value;

    public UserId(String value) {
        // 値が空じゃないかチェック
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ユーザーIDは必須です");
        }
        // UUIDの形式チェック
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ユーザーIDの形式が不正です:" + value);
        }
        this.value = value;
    }

    /**
     * 新しいユーザーIDを生成する静的ファクトリメソッド
     */
    public static UserId generate() {
        return new UserId(UUID.randomUUID().toString());
    }

    /**
     * 値を取得する
     */
    public String getValue() {
        return value;
    };

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserId userId = (UserId) o;
        return Objects.equals(value, userId.value);
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
