package com.queencastle.weixin.controllers.goods;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.queencastle.dao.model.goods.DemandSupplyType;
import com.queencastle.dao.utils.DateUtils;

public class DemandSupplyVO implements Serializable {
	private static final long serialVersionUID = 2047640073491072164L;
	private String id;
	private String username;

	private Date createdAt;
	private Date startDate;
	/** 结束时间 */
	private Date endDate;
	/** 产品 */
	private String productId;
	private String productName;

	private List<String> productImgs;
	/** 数量 */
	private int amount;
	/** 价格，这里以单位分计算，显示给用户的是按元计算的 */
	private int price;
	/** 供需类型 */
	private DemandSupplyType dsType;
	/** 备注 */
	private String memo;
	private String address;
	private int praiseCnt;
	private boolean view;
	private int dayGap;
	private String img;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<String> getProductImgs() {
		return productImgs;
	}

	public void setProductImgs(List<String> productImgs) {
		this.productImgs = productImgs;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPraiseCnt() {
		return praiseCnt;
	}

	public void setPraiseCnt(int praiseCnt) {
		this.praiseCnt = praiseCnt;
	}

	public boolean isView() {
		return view;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	public int getDayGap() {
		int dayGap = DateUtils.getDayGap(this.getEndDate(), this.getStartDate());
		return dayGap;
	}

	public void setDayGap(int dayGap) {
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
