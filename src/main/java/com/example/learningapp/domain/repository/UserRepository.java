package com.example.learningapp.domain.repository;

import java.util.Optional;
import com.example.learningapp.domain.model.user.Username;
import com.example.learningapp.domain.model.user.Email;
import com.example.learningapp.domain.model.user.User;
import com.example.learningapp.domain.model.user.UserId;

public interface UserRepository {

    /**
     * ユーザーを保存する
     * 
     * @param user 保存するユーザー
     * @return 保存後のユーザー
     * 
     */
    User save(User user);

    /**
     * ユーザーIDでユーザーを検索する
     * 
     * @param userId
     * @return ユーザーが存在する場合は該当ユーザー、存在しない場合は空のOptional
     * 
    */
    Optional<UserId> findById(UserId id);

    /**
     * ユーザー名でユーザーを検索する
     * 
     * ログイン処理や、ユーザーの重複確認チェックで使用する
     * ユーザー名が一意である必要があるため、結果は1件または0件で返す
     * 
     * @param username ユーザー名
     * @return ユーザーが存在する場合は該当ユーザー、存在しない場合は空のOptional
     */
    Optional<Username> findByUsername(Username username);

    /**
     * メールアドレスでユーザーを検索する
     * 
     * パスワードリセットなどで使用する
     * メールアドレスは一意である必要がある
     * 
     * @param email メールアドレス
     * @return ユーザーが存在する場合はユーザー、存在しない場合は空のOptional
     */
    Optional<Email> findByEmail(Email email);

    /**
     * ユーザー名がすでに使用されているか確認する
     * 新規ユーザー登録時や、ユーザー名変更時の重複チェックに使用する
     * 
     * @param username チェック対象のユーザー名
     * @return すでに存在する場合はtrue
     */
    boolean existsByUsername(Username username);

    /**
     * ユーザーのメールアドレスがすでに使用されているか確認する
     * 新規ユーザー登録時や、メールアドレス変更時の重複チェックに使用する
     * 
     * @param email チェック対象のメールアドレス
     * @return すでに存在する場合はtrue
     */
    boolean existsByEmail(Email email);

    /**
     * ユーザーを削除する
     * 
     * @param userId 削除対象のユーザーのID
     */
    void delete(UserId id);
}