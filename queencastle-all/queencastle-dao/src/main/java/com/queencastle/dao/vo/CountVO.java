package com.queencastle.dao.vo;

/**
 * 鉴于SQL的缺陷，定义字符串查询字段和对应的统计数
 * 
 * @author YuDongwei
 *
 */
public class CountVO {

    private String id;
    private Integer cnt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }



}
