package com.example.learningapp.domain.model.user;

import java.time.LocalDateTime;

/**
 * ユーザーを表現するドメインエンティティ
 */
public class User {
    private final UserId id;
    private Username username;
    private Email email;
    private HashedPassword password;
    private String displayName;
    private String bio;
    private String avatarUrl;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(UserId id, Username username, Email email, HashedPassword password,
            LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 新規ユーザーを作成する静的ファクトリメソッド
     * 
     */
    public static User create(Username username, Email email, HashedPassword password) {
        UserId userId = UserId.generate();
        return new User(userId, username, email, password, LocalDateTime.now());
    }

    /**
     * 既存のユーザーを復元するファクトリメソッド
     * データベースから取得したデータをもとにエンティティを復元する際に使用する
     * 
     */
    public static User reconstruct(UserId id, Username username, Email email,
            HashedPassword password, String displayName, String bio, String avatarUrl,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        User user = new User(id, username, email, password, createdAt);
        user.displayName = displayName;
        user.bio = bio;
        user.displayName = displayName;
        user.bio = bio;
        user.avatarUrl = avatarUrl;
        user.updatedAt = updatedAt;
        return user;
    }

    /**
     * プロフィールを更新する
     * ユーザーが自身のプロフィールを更新する際に使用する
     * 
     */
    public void userProfile(String displayName, String bio, String avatarUrl) {
        if (displayName != null) {
            this.displayName = displayName.trim();
        }
        if (bio != null) {
            this.bio = bio.trim();
        }
        if (avatarUrl != null) {
            this.avatarUrl = avatarUrl.trim();
        }
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * ユーザー名を変更する
     * 
     */
    public void changeUsername(Username newUsername) {
        this.username = newUsername;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * メールアドレスを変更する
     * 
     */
    public void changeEmail(Email newEmail) {
        this.email = newEmail;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * パスワードを変更する
     * 
     */
    public void changePassword(HashedPassword newPassword) {
        this.password = newPassword;
        this.updatedAt = LocalDateTime.now();
    }
    
    public UserId getId() {
        return id;
    }

    public Username getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public HashedPassword getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getBio() {
        return bio;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * エンティティの同一性はIDで判断する
     * 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode(); 
    }
}
