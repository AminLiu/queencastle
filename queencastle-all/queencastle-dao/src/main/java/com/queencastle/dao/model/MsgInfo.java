package com.queencastle.dao.model;


/**
 * 消息发布对象
 * 
 * @author YuDongwei
 *
 */
public class MsgInfo extends BaseModel {

    private static final long serialVersionUID = -6830676111521942965L;
    /** 消息发布人 */
    private String userId;
    /** 消息的标题 */
    private String title;
    /** 消息内容 */
    private String content;
    /** 相关图片 */
    private String images;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }



}
