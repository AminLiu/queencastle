package com.queencastle.dao.model.shop;

import java.util.List;

import com.queencastle.dao.model.BaseModel;

/***
 * 订单
 * 
 * @author YuDongwei
 *
 */
public class ShopOrder extends BaseModel {

    private static final long serialVersionUID = -1312967007488864400L;
    private List<OrderItem> item;
    private double total;
    private String userId;
    private String addressId;

 
    public List<OrderItem> getItem() {
        return item;
    }

    public void setItem(List<OrderItem> item) {
        this.item = item;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

 
}
