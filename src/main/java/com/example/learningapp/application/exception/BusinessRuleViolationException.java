package com.example.learningplatform.application.exception;

/**
 * ビジネスルール違反の例外 例: 既に使われているユーザー名で登録しようとした場合
 * 
 */
public class BusinessRuleViolationException extends RuntimeException {
    public BusinessRuleViolationException(String message) {
        super(message);
    }
}
