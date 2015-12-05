package com.queencastle.dao.model.bbs;

import com.queencastle.dao.model.BaseModel;
import com.queencastle.dao.model.User;

/**
 * BBS论坛文章内容
 * 
 * @author YuDongwei
 *
 */
public class BBSArticle extends BaseModel {

	private static final long serialVersionUID = 3374398087620525969L;

	/** 板块编号 */
	private String boardId;
	/** 标题 */
	private String title;
	/** 图片 */
	private String img;
	/** 内容 */
	private String content;

	/** 关注 */
	private Integer attentionCnt;
	/** 点赞 */
	private Integer praiseCnt;
	/** 页面浏览数 */
	private Long pageView;
	private Integer commentCnt;
	/** 置顶 */
	private Boolean top;
	/** 文章类型 */
	private ArticleType type;
	/** 作者 */
	private User author;

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAttentionCnt() {
		return attentionCnt;
	}

	public void setAttentionCnt(Integer attentionCnt) {
		this.attentionCnt = attentionCnt;
	}

	public Integer getPraiseCnt() {
		return praiseCnt;
	}

	public void setPraiseCnt(Integer praiseCnt) {
		this.praiseCnt = praiseCnt;
	}

	public Long getPageView() {
		return pageView;
	}

	public void setPageView(Long pageView) {
		this.pageView = pageView;
	}

	public Integer getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(Integer commentCnt) {
		this.commentCnt = commentCnt;
	}

	public Boolean getTop() {
		return top;
	}

	public void setTop(Boolean top) {
		this.top = top;
	}

	public ArticleType getType() {
		return type;
	}

	public void setType(ArticleType type) {
		this.type = type;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
