package com.example.learningapp.domain.model.user;

import java.util.Objects;

/**
 * ハッシュ化されたパスワードを表現するValue Object
 */
public class HashedPassword {
    private final String value;

    /**
     * 注: このコンストラクタに渡す値は、既にハッシュ化されている必要がある
     * 生のパスワードは渡さない
     */
    public HashedPassword(String hashedValue) {
        if (hashedValue == null || hashedValue.isBlank()) {
            throw new IllegalArgumentException("パスワードは必須です");
        }
        this.value = hashedValue;
    }

    /**
     * ハッシュ化されたパスワードを取得
     */
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        HashedPassword that = (HashedPassword) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /**
     * toStringは使用できないようにする
     * パスワードハッシュは機密情報のためで、ログなどに出力されないようにする
     * 
     */
    @Override
    public String toString() {
        return "[PROTECTED]";
    }
}
