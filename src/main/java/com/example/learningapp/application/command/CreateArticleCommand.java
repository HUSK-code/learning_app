package com.example.learningapp.application.command;

import lombok.Getter;

import java.util.Set;

/**
 * 記事作成ユースケースへの入力データ
 * 
 */
@Getter
public class CreateArticleCommand {

    private final String authorId;
    private final String title;
    private final String content;
    private final String contentType; // "MARKDOWN" または "RICH_TEXT"
    private final Set<String> tags; // タグ名のセット

    public CreateArticleCommand(String authorId, String title, String content, String contentType,
            Set<String> tags) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.contentType = contentType;
        this.tags = tags;
    }
}
