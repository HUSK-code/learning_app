package com.example.learningapp.domain.model.article;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Collections;
import java.util.Set;
import com.example.learningapp.domain.model.tag.Tag;
import com.example.learningapp.domain.model.user.UserId;

// 記事を表現するドメインエンティティ
public class Article {
    private final ArticleId id;
    private ArticleTitle title;
    private ArticleContent content;
    private ArticleStatus status;
    private final UserId authorId;
    private final Set<Tag> tags;
    private int likeCount;
    private int viewCount;
    private LocalDateTime publishedAt;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final int MAX_TAGS = 5;

    private Article(ArticleId id, ArticleTitle title, ArticleContent content, UserId authorId,
            LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.status = ArticleStatus.DRAFT;
        this.tags = new HashSet<>();
        this.likeCount = 0;
        this.viewCount = 0;
        this.createdAt = createdAt;
        this.updatedAt = LocalDateTime.now();
        this.publishedAt = null;
    }

    // 新規記事を作成するメソッド
    public static Article create(ArticleTitle title, ArticleContent content, UserId authorId) {
        ArticleId id = ArticleId.generateNewId();
        return new Article(id, title, content, authorId, LocalDateTime.now());
    }

    // 既存記事を復元するメソッド
    public static Article reconstruct(ArticleId id, ArticleTitle title, ArticleContent content,
            UserId authorId, ArticleStatus status, Set<Tag> tags, int likeCount, int viewCount,
            LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime publishedAt) {
        Article article = new Article(id, title, content, authorId, createdAt);
        article.status = status;
        article.tags.addAll(tags);
        article.likeCount = likeCount;
        article.viewCount = viewCount;
        article.updatedAt = updatedAt;
        article.publishedAt = publishedAt;
        return article;
    }

    // 既存記事を更新するメソッド
    public void update(ArticleTitle newTitle, ArticleContent newContent) {
        this.title = newTitle;
        this.content = newContent;
        this.updatedAt = LocalDateTime.now();
    }

    // 記事を公開するメソッド
    public void publish() {
        if (this.status == ArticleStatus.PUBLISHED) {
            throw new IllegalStateException("すでに公開済みの記事です");
        }

        if (!this.status.canTransitionTo(ArticleStatus.PUBLISHED)) {
            throw new IllegalStateException("現在のステータスからは公開できません" + this.status);
        }

        if (this.content.isEmpty()) {
            throw new IllegalStateException("本文が空の記事は公開できません");
        }

        this.status = ArticleStatus.PUBLISHED;
        this.publishedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 記事をアーカイブするメソッド
    public void archive() {
        if (!this.status.canTransitionTo(ArticleStatus.ARCHIVED)) {
            throw new IllegalStateException("現在のステータスからはアーカイブできません。" + this.status);
        }
        this.status = ArticleStatus.ARCHIVED;
        this.updatedAt = LocalDateTime.now();
    }

    // タグを追加するメソッド
    public void addTag(Tag tag) {
        if (this.tags.size() >= MAX_TAGS) {
            throw new IllegalStateException("記事に設定できるタグは" + MAX_TAGS + "個までです");
        }
        this.tags.add(tag);
    }

    // タグを削除するメソッド
    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    // 全てのタグをクリアするメソッド
    public void clearAllTags() {
        this.tags.clear();
    }

    // いいねを増やすメソッド
    public void incrementLike() {
        this.likeCount++;
    }

    // いいねを減らすメソッド
    public void decrementLike() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }

    // 閲覧数を増やすメソッド
    public void incrementView() {
        this.viewCount++;
    }

    // この記事の作成者か確認するメソッド
    public boolean isAuthor(UserId userId) {
        return this.authorId.equals(userId);
    }

    // この記事が公開されているか確認するメソッド
    public boolean isPublished() {
        return this.status == ArticleStatus.PUBLISHED;
    }

    // ゲッター
    public ArticleId getId() {
        return id;
    }

    public ArticleTitle getTitle() {
        return title;
    }

    public ArticleContent getContent() {
        return content;
    }

    public UserId getAuthorId() {
        return authorId;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime publishedAt() {
        return publishedAt;
    }

    // エンティティの同一性を判断
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Article article = (Article) o;
        return id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}















