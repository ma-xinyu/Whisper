package com.tape.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Article implements Serializable {

    private Integer articleId;
    private Integer articleUserId;
    private String articleTitle;
    private Integer articleViewCount;
    private Integer articleCommentCount;
    private Integer articleLikeCount;
    private Date articleCreateTime;
    private Date articleUpdateTime;
    private Integer articleIsComment;
    private Integer articleStatus;
    private Integer articleOrder;
    private String articleContent;
    private String articleSummary;
    private String articleThumbnail;
    private User user;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleUserId() {
        return articleUserId;
    }

    public void setArticleUserId(Integer articleUserId) {
        this.articleUserId = articleUserId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Integer getArticleViewCount() {
        return articleViewCount;
    }

    public void setArticleViewCount(Integer articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    public Integer getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Integer articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public Integer getArticleLikeCount() {
        return articleLikeCount;
    }

    public void setArticleLikeCount(Integer articleLikeCount) {
        this.articleLikeCount = articleLikeCount;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public Date getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(Date articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public Integer getArticleIsComment() {
        return articleIsComment;
    }

    public void setArticleIsComment(Integer articleIsComment) {
        this.articleIsComment = articleIsComment;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Integer getArticleOrder() {
        return articleOrder;
    }

    public void setArticleOrder(Integer articleOrder) {
        this.articleOrder = articleOrder;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getArticleThumbnail() {
        return articleThumbnail;
    }

    public void setArticleThumbnail(String articleThumbnail) {
        this.articleThumbnail = articleThumbnail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleUserId=" + articleUserId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleViewCount=" + articleViewCount +
                ", articleCommentCount=" + articleCommentCount +
                ", articleLikeCount=" + articleLikeCount +
                ", articleCreateTime=" + articleCreateTime +
                ", articleUpdateTime=" + articleUpdateTime +
                ", articleIsComment=" + articleIsComment +
                ", articleStatus=" + articleStatus +
                ", articleOrder=" + articleOrder +
                ", articleContent='" + articleContent + '\'' +
                ", articleSummary='" + articleSummary + '\'' +
                ", articleThumbnail='" + articleThumbnail + '\'' +
                ", user=" + user +
                '}';
    }
}
