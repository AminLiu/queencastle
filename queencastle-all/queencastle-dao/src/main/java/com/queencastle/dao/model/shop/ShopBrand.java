package com.queencastle.dao.model.shop;

import com.queencastle.dao.model.BaseModel;

/**
 * 产品品牌
 * 
 * @author YuDongwei
 *
 */
public class ShopBrand extends BaseModel {
	private static final long serialVersionUID = 7600370114752730075L;
	/** 品牌名称 */
	private String cname;
	/** 品牌图案 */
	private String img;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
