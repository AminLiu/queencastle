package com.queencastle.dao.model.weixin;

import com.queencastle.dao.model.BaseModel;

public class Agreement extends BaseModel {

	/**
	 * 保存协议的用户
	 */
	private static final long serialVersionUID = 3953949979514986599L;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String type;

}
