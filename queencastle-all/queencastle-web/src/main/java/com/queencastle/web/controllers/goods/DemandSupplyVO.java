package com.queencastle.web.controllers.goods;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.queencastle.dao.model.goods.DemandSupplyType;
import com.queencastle.dao.utils.SysDateSerializer;

public class DemandSupplyVO {
    private String id;
    private String cname;
    @JsonSerialize(using = SysDateSerializer.class)
    private Date startDate;
    @JsonSerialize(using = SysDateSerializer.class)
    private Date endDate;
    @JsonSerialize(using = SysDateSerializer.class)
    private Date createdAt;
    private int amount;
    private int price;
    private DemandSupplyType dsType;
    private String memo;
    private int check;

    public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public DemandSupplyType getDsType() {
        return dsType;
    }

    public void setDsType(DemandSupplyType dsType) {
        this.dsType = dsType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


}
