package com.example.learningapp.application.command;

import lombok.Getter;

/**
 * ユーザー登録ユースケースへの入力データ
 *
 */
@Getter
public class RegisterUserCommand {

    private final String username;
    private final String email;
    private final String rawPassword; // 生のパスワード（ハッシュ化前）

    public RegisterUserCommand(String username, String email, String rawPassword) {
        this.username = username;
        this.email = email;
        this.rawPassword = rawPassword;
    }
}
