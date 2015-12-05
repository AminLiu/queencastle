package com.queencastle.dao.model;

/***
 * 是否归档或者删除
 * 
 * @author YuDongwei
 *
 */
public interface Archive {

    boolean isArchive();

    void setArchive(boolean archive);

}
