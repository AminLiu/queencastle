package com.queencastle.dao.model.goods;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.queencastle.dao.model.BaseModel;
import com.queencastle.dao.utils.SysDateSerializer;

/**
 * 供需消息
 * 
 *
 * @author YuDongwei
 */
public class DemandSupplyInfo extends BaseModel {

	private static final long serialVersionUID = 5537653062959807383L;
	/** 发布消息的人 */
	private String userId;
	/** 开始时间 */
	@JsonSerialize(using = SysDateSerializer.class)
	private Date startDate;
	/** 结束时间 */
	@JsonSerialize(using = SysDateSerializer.class)
	private Date endDate;
	/** 产品 */
	private Product product;
	/** 数量 */
	private int amount;
	/** 价格，这里以单位分计算，显示给用户的是按元计算的 */
	private int price;
	/** 供需类型 */
	private DemandSupplyType dsType;
	/** 备注 */
	private String memo;
	/** 审核状态 0---未通过 1---未审核 2---审核通过 **/
	private int check;
	private String address;
	/** 点赞数 */
	private int praiseCnt;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public int getPraiseCnt() {
		return praiseCnt;
	}

	public void setPraiseCnt(int praiseCnt) {
		this.praiseCnt = praiseCnt;
	}

}
