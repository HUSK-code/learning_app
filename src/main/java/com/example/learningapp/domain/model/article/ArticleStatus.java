package com.example.learningapp.domain.model.article;

// 記事のステータスを表現
public enum ArticleStatus {
    DRAFT("下書き"),
    PUBLISHED("公開済み"),
    ARCHIVED("アーカイブ済み");

    private final String displayName;

    ArticleStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    // 現在のステータスから指定されたステータスへ変更が可能かを判定
    public boolean canTransitionTo(ArticleStatus newStatus) {
        switch (this) {
            // 下書きステータスからは公開もしくはアーカイブにステータス遷移可能
            case DRAFT:
                return newStatus == PUBLISHED || newStatus == ARCHIVED;

            // 公開ステータスからはアーカイブのみにステータス遷移可能
            case PUBLISHED:
                return newStatus == ARCHIVED;

            // アーカイブからはステータス遷移不可（再度公開するときは新規作成）
            case ARCHIVED:
                return false;
            default:
                return false;
        }
    }
}