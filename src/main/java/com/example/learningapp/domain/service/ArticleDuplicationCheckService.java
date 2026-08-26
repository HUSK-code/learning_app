package com.example.learningapp.domain.service;

import com.example.learningapp.domain.model.article.ArticleTitle;
import com.example.learningapp.domain.model.user.UserId;
import com.example.learningapp.domain.repository.ArticleRepository;

/**
 * 「同じユーザーが同じタイトルの記事を複数作成できない」というビジネスルールを実装する
 * 
 */
public class ArticleDuplicationCheckService {
    private final ArticleRepository articleRepository;

    /**
     * @param articleRepository 記事リポジトリ
     */
    public ArticleDuplicationCheckService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * 記事が重複しているかチェックする 同じユーザーがすでに同じタイトルの記事を持っている場合、重複していると判断する
     * 
     * @param title 記事のタイトル
     * @param authorId 記事を書いたユーザー
     * @return 重複している場合はtrue
     */
    public boolean isDuplicate(ArticleTitle title, UserId authorId) {
        return articleRepository.existsByAuthorIdAndTitle(authorId, title.getValue());
    }
}
