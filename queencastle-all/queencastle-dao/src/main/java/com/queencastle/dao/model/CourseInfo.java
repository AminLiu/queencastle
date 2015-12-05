package com.queencastle.dao.model;

import java.util.Date;

/**
 * 课程内容
 * 
 * @author YuDongwei
 *
 */
public class CourseInfo extends BaseModel {

    private static final long serialVersionUID = -6876080746449442581L;
    // TODO:讲课人？？？？？？？？？？
    /** 标题 */
    private String title;
    /** 开播时间 */
    private Date startShow;
    /** 结束显示时间 */
    private Date endShow;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartShow() {
        return startShow;
    }

    public void setStartShow(Date startShow) {
        this.startShow = startShow;
    }

    public Date getEndShow() {
        return endShow;
    }

    public void setEndShow(Date endShow) {
        this.endShow = endShow;
    }



}
