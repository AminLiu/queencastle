package com.queencastle.dao.vo;

/**
 * 复杂查询对象封装，包含查询的字段以及字段升降序
 * 
 * @author YuDongwei
 *
 */
public class SearchVO {
    /** 查询的字段 */
    private String field;
    /** 按照查询字段升序还是降序 */
    private OrderType orderType;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }



}
