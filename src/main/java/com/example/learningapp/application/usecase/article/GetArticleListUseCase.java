package com.example.learningapp.application.usecase.article;

import com.example.learningapp.application.dto.ArticleDto;
import com.example.learningapp.domain.model.article.ArticleStatus;
import com.example.learningapp.domain.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 記事一覧取得ユースケース
 *
 * 読み取り専用の操作なので、@Transactional(readOnly = true)を使います。 readOnly = true にすることで、Hibernateの最適化が働き、
 * パフォーマンスが向上します。また、誤って更新操作が行われることを防ぎます。
 */
@Service
@RequiredArgsConstructor
public class GetArticleListUseCase {

    private final ArticleRepository articleRepository;

    /**
     * 公開済み記事の一覧を取得する
     *
     */
    @Transactional(readOnly = true)
    public List<ArticleDto> execute(int page, int size) {
        return articleRepository.findPublishedArticles(page, size).stream().map(ArticleDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 公開済み記事の総件数を取得する
     *
     * ページネーションのUI（「全○ページ」の表示）のために使用します。
     */
    @Transactional(readOnly = true)
    public long countPublishedArticles() {
        return articleRepository.countByStatus(ArticleStatus.PUBLISHED);
    }
}
