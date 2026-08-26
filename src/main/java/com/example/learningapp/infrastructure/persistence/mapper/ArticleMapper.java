package com.example.learningapp.infrastructure.persistence.mapper;

import com.example.learningapp.domain.model.article.*;
import com.example.learningapp.domain.model.tag.Tag;
import com.example.learningapp.domain.model.user.UserId;
import com.example.learningapp.infrastructure.persistence.entity.ArticleJpaEntity;
import com.example.learningapp.infrastructure.persistence.entity.TagJpaEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * ArticleドメインエンティティとArticleJpaEntityを相互変換するMapper
 */
@Component
public class ArticleMapper {

    /**
     * JPAエンティティをドメインエンティティに変換する
     */
    public Article toDomainEntity(ArticleJpaEntity jpa) {

        // タグの変換: TagJpaEntity → Tag（Value Object）
        Set<Tag> tags = jpa.getTags().stream().map(tagJpa -> new Tag(tagJpa.getName()))
                .collect(Collectors.toSet());

        return Article.reconstruct(new ArticleId(jpa.getId()), new ArticleTitle(jpa.getTitle()),
                new ArticleContent(jpa.getContent()),
                new UserId(jpa.getAuthorId()), ArticleStatus.valueOf(jpa.getStatus()), tags,
                jpa.getLikeCount(), jpa.getViewCount(), jpa.getCreatedAt(), jpa.getUpdatedAt(),
                jpa.getPublishedAt());
    }

    /**
     * ドメインエンティティをJPAエンティティに変換する
     */
    public ArticleJpaEntity toJpaEntity(Article article) {

        ArticleJpaEntity jpa = new ArticleJpaEntity(article.getId().getValue(),
                article.getAuthorId().getValue(), article.getTitle().getValue(),
                article.getContent().getValue(),
                article.getStatus().name(), article.getLikeCount(), article.getViewCount(),
                article.getCreatedAt(), article.getUpdatedAt(), article.getPublishedAt());

        // タグの変換: Tag（Value Object）→ TagJpaEntity
        Set<TagJpaEntity> tagEntities =
                article.getTags().stream().map(tag -> new TagJpaEntity(UUID.randomUUID().toString(),
                        tag.getValue(), 0, LocalDateTime.now())).collect(Collectors.toSet());

        jpa.setTags(tagEntities);
        return jpa;
    }
}
