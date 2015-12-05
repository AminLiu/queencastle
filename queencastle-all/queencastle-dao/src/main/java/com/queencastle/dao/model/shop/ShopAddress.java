package com.queencastle.dao.model.shop;

import com.queencastle.dao.model.BaseModel;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;

/**
 * 收货地址
 * 
 * @author YuDongwei
 *
 */
public class ShopAddress extends BaseModel {

	private static final long serialVersionUID = -7269358384644279955L;
	/** 省份 */
	private Province province;
	/** 市 */
	private City city;
	/** 详述 */
	private String detail;
	/** 收货人 */
	private String userId;

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
