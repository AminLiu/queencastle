package com.queencastle.weixin;

public class ResponseObject<T> {
	private T data;
	private GlobalCode code = GlobalCode.SUCCESS;
	private String message;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public GlobalCode getCode() {
		return code;
	}

	public void setCode(GlobalCode code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
