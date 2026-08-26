package com.example.learningapp.infrastructure.persistence.repository;

import com.example.learningapp.domain.model.article.Article;
import com.example.learningapp.domain.model.article.ArticleId;
import com.example.learningapp.domain.model.article.ArticleStatus;
import com.example.learningapp.domain.model.tag.Tag;
import com.example.learningapp.domain.model.user.UserId;
import com.example.learningapp.domain.repository.ArticleRepository;
import com.example.learningapp.infrastructure.persistence.entity.ArticleJpaEntity;
import com.example.learningapp.infrastructure.persistence.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ArticleRepositoryインターフェースのJPA実装
 */
@Repository
@RequiredArgsConstructor
public class JpaArticleRepositoryImpl implements ArticleRepository {

    private final JpaArticleRepository jpaArticleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public Article save(Article article) {
        ArticleJpaEntity jpaEntity = articleMapper.toJpaEntity(article);
        ArticleJpaEntity saved = jpaArticleRepository.save(jpaEntity);
        return articleMapper.toDomainEntity(saved);
    }

    @Override
    public Optional<Article> findById(ArticleId id) {
        return jpaArticleRepository.findById(id.getValue()).map(articleMapper::toDomainEntity);
    }

    @Override
    public List<Article> findByAuthorId(UserId authorId) {
        return jpaArticleRepository.findByAuthorId(authorId.getValue()).stream()
                .map(articleMapper::toDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Article> findByTag(Tag tag) {
        return jpaArticleRepository.findByTagName(tag.getValue()).stream()
                .map(articleMapper::toDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Article> findByPublishedArticles(int page, int size) {

        // ページネーションの設定
        // PageRequest: ページ番号、1ページあたりの件数、ソート順を指定
        PageRequest pageable =
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "publishedAt") // 公開日時の降順
                );

        return jpaArticleRepository.findByStatus("PUBLISHED", pageable).stream()
                .map(articleMapper::toDomainEntity).collect(Collectors.toList());
    }

    @Override
    public long countByStatus(ArticleStatus status) {
        return jpaArticleRepository.countByStatus(status.name());
    }

    @Override
    public void delete(ArticleId id) {
        jpaArticleRepository.deleteById(id.getValue());
    }

    @Override
    public boolean existsByAuthorIdAndTitle(UserId authorId, String title) {
        return jpaArticleRepository.existsByAuthorIdAndTitle(authorId.getValue(), title);
    }
}
