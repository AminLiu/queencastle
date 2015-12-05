package com.queencastle.dao.model.data;

import com.queencastle.dao.model.BaseModel;

/**
 * 销售数据整理
 * 
 * @author YuDongwei
 *
 */
public class SaleData extends BaseModel {

    private static final long serialVersionUID = -1666344832587843703L;

    private String orderNo;
    private String userName;
    private String address;
    private String phone;
    private String province;
    private String city;
    private String area;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }



}
