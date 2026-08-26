package com.example.learningapp.application.dto;

import com.example.learningapp.domain.model.article.Article;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 記事情報の出力データ
 */
@Getter
public class ArticleDto {

    private final String id;
    private final String authorId;
    private final String title;
    private final String content;
    private final String contentType;
    private final String status;
    private final Set<String> tags;
    private final int likeCount;
    private final int viewCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime publishedAt;

    private ArticleDto(String id, String authorId, String title, String content, String status,
            Set<String> tags, int likeCount, int viewCount, LocalDateTime createdAt,
            LocalDateTime publishedAt) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.status = status;
        this.tags = tags;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.publishedAt = publishedAt;
    }

    public static ArticleDto from(Article article) {
        // タグのSet<Tag> → Set<String>に変換
        Set<String> tagNames =
                article.getTags().stream().map(tag -> tag.getValue()).collect(Collectors.toSet());

        return new ArticleDto(article.getId().getValue(), article.getAuthorId().getValue(),
                article.getTitle().getValue(), article.getContent().getValue(),
                article.getStatus().name(), tagNames, article.getLikeCount(),
                article.getViewCount(), article.getCreatedAt(), article.getPublishedAt());
    }
}
