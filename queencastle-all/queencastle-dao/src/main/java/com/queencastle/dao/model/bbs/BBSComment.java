package com.queencastle.dao.model.bbs;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.BaseModel;

/**
 * 文章评论
 * 
 * @author YuDongwei
 *
 */
public class BBSComment extends BaseModel {

    private static final long serialVersionUID = -272557311437203219L;
    /** 文章编号 */
    private String articleId;
    /** 评论内容 */
    private String content;
    /** 评论留言的人 */
    private String userId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    

}
