package com.example.learningapp.application.exception;

/**
 * リソースが見つからなかった場合の例外 例: 存在しないIDで記事を検索した場合
 * 
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
