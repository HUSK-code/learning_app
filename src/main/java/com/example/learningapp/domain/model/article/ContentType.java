package com.example.learningapp.domain.model.article;

// 記事のコンテンツタイプを表現
public enum ContentType {
    MARKDOWN("マークダウン形式"), RICH_TEXT("リッチテキスト形式");

    private final String displayName;

    ContentType(String name) {
        this.displayName = name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
