package com.queencastle.dao.model.goods;

import com.queencastle.dao.model.BaseModel;
import com.queencastle.dao.model.area.Area;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;

/**
 * 收货地址
 * 
 * @author YuDongwei
 *
 */
public class UserGoodsAddress extends BaseModel {

	private static final long serialVersionUID = -5658260635402551978L;

	private String userId;
	private Province province;
	private City city;
	private Area area;
	private String address;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
