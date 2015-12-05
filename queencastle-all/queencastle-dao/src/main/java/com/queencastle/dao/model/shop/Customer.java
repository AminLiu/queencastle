package com.queencastle.dao.model.shop;

import com.queencastle.dao.model.BaseModel;

/**
 * 商家
 * 
 * @author YuDongwei
 *
 */
public class Customer extends BaseModel {

    private static final long serialVersionUID = 7447881952778198553L;
	/* 商家名字 */
	private String customerName;
	/* 商家联系方式 */
	private String customerTel;
	/* 商家备注 */
	private String customerSex;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public String getCustomerSex() {
		return customerSex;
	}

	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}


}
