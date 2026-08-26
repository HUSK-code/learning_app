package com.example.learningapp.domain.repository;

import java.util.Optional;
import com.example.learningapp.domain.model.article.Article;
import com.example.learningapp.domain.model.article.ArticleId;
import com.example.learningapp.domain.model.article.ArticleStatus;
import com.example.learningapp.domain.model.tag.Tag;
import com.example.learningapp.domain.model.user.UserId;
import java.util.List;

// 記事の永続化を抽象化
public interface ArticleRepository {
    /**
     * 記事を保存する
     * 新規作成、更新、どちらでも使用可
     * 
     * @param article 保存する記事
     * @return 保存後の記事
     */
    Article save(Article article);

    /**
    * 記事IDで特定の記事を検索する
    * 記事の詳細画面を表示する際や、記事を編集する際など、現在の状態を取得する際に使用する
    * 
    * @param id 記事ID
    * @return 記事が存在する場合は当該記事、存在しない場合はOptional
    */
    Optional<Article> findById(ArticleId id);

    /**
    * 特定のユーザーが書いた記事を検索する
    * ユーザープロフィールページでそのユーザーが書いた記事一覧を取得する際に使用する
    * 
    * @param authorId 著者のユーザーID
    * @return 該当する記事の全件リスト
    */
    List<Article> findByAuthorId(UserId authorId);

    /**
    * 特定のタグが付いた記事を検索する
    * 
    * @param tag 検索するタグ
    * @return 該当する記事の全件リスト
    */
    List<Article> findByTag(Tag tag);

    /**
    * 公開されているすべての記事を取得する
    * トップページや記事一覧ページで使用する
    * 
    * @param page ページ番号
    * @param size 1ページあたりの記事数
    * @return 該当する記事の全件リスト
    */
    List<Article> findByPublishedArticles(int page, int size);

    /**
     * 特定のステータスの記事数を取得する
     * ページネーションのための総ページ数を計算する際に使用する
     * 
     * @param status 記事のステータス
     * @return 該当する記事の総数
     */
    long countByStatus(ArticleStatus status);

    /**
    * 特定の記事を削除する
    * 
    * param id 削除する記事のID
    */
    void delete(ArticleId id);

    /**
    * 特定のユーザーが特定のタイトルの記事を既に持っているか確認
    * 記事の重複をチェックする際に使用する
    * 
    * @param authorId 記事を書いたユーザーのID
    * @param title 記事のタイトル
    * @return 既に存在する場合はtrueを返す
    */
    boolean existsByAuthorIdAndTitle(UserId authorId, String title);
}
