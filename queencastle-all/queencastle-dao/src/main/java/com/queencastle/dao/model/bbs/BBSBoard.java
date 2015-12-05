package com.queencastle.dao.model.bbs;

import com.queencastle.dao.model.BaseModel;

/**
 * 板块
 * 
 * @author YuDongwei
 *
 */
public class BBSBoard extends BaseModel {

    private static final long serialVersionUID = 5062010519133652142L;
    /** 标题 */
    private String title;
    /** 小图片 */
    private String img;

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



}
