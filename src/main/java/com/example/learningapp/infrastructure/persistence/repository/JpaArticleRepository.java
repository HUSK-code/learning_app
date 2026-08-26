package com.example.learningapp.infrastructure.persistence.repository;

import com.example.learningapp.infrastructure.persistence.entity.ArticleJpaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPAのリポジトリインターフェース（記事）
 * 
 */
public interface JpaArticleRepository extends JpaRepository<ArticleJpaEntity, String> {

    // author_idで記事を検索
    List<ArticleJpaEntity> findByAuthorId(String authorId);

    // ステータスで記事を検索
    List<ArticleJpaEntity> findByStatus(String status, Pageable pageable);

    // ステータスで件数を取得
    long countByStatus(String status);

    // authorIdとtitleで存在確認
    boolean existsByAuthorIdAndTitle(String authorId, String title);

    /**
     * タグ名で記事を検索する
     *
     */
    @Query("""
            SELECT DISTINCT a FROM ArticleJpaEntity a
            JOIN a.tags t
            WHERE t.name = :tagName
            """)
    List<ArticleJpaEntity> findByTagName(@Param("tagName") String tagName);
}
