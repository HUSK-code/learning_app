package com.example.learningapp.application.usecase.article;

import com.example.learningapp.application.command.CreateArticleCommand;
import com.example.learningapp.application.dto.ArticleDto;
import com.example.learningapp.domain.model.article.*;
import com.example.learningapp.domain.model.tag.Tag;
import com.example.learningapp.domain.model.user.UserId;
import com.example.learningapp.domain.repository.ArticleRepository;
import com.example.learningapp.domain.repository.UserRepository;
import com.example.learningapp.domain.service.ArticleDuplicationCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 記事作成ユースケース
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CreateArticleUseCase {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    // Domain Serviceを注入します
    private final ArticleDuplicationCheckService duplicationCheckService;

    /**
     * 記事作成を実行する
     *
     * ビジネスフロー: 1. 著者の存在確認（存在しないユーザーは記事を作れない） 2. 記事タイトルの重複チェック（同じユーザーが同タイトルは不可） 3. Value
     * Objectの生成（バリデーション） 4. Articleドメインエンティティの生成 5. タグの追加 6. データベースへの保存 7. DTOに変換して返却
     */
    @Transactional
    public ArticleDto execute(CreateArticleCommand command) {

        log.info("記事作成開始: authorId={}, title={}", command.getAuthorId(), command.getTitle());

        UserId authorId = new UserId(command.getAuthorId());

        // ステップ1: 著者の存在確認
        // 存在しないユーザーIDで記事を作ろうとした場合に備えつ
        userRepository.findById(authorId).orElseThrow(
                () -> new ResourceNotFoundException("ユーザーが見つかりません: " + command.getAuthorId()));

        // ステップ2: 記事タイトルの重複チェック

        // ステップ3 & 4: Value Object生成 + ドメインエンティティ生成
        ArticleContent content = new ArticleContent(command.getContent());

        // Articleエンティティを生成します（初期状態はDRAFT）
        Article article = Article.create(title, content, authorId);

        // ステップ5: タグの追加
        // commandに含まれるタグ名を、Tag Value Objectに変換して追加します
        // Articleエンティティ内でタグの上限（5つ）チェックが行われます
        if (command.getTags() != null) {
            for (String tagName : command.getTags()) {
                article.addTag(new Tag(tagName));
            }
        }

        // ステップ6: データベースへの保存
        Article savedArticle = articleRepository.save(article);

        log.info("記事作成完了: articleId={}", savedArticle.getId().getValue());

        // ステップ7: DTOに変換して返却
        return ArticleDto.from(savedArticle);
    }
}
