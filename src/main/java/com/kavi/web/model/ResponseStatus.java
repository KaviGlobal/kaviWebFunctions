package com.kavi.web.model;

public class ResponseStatus {

	private Object data;

	private String status;

	public ResponseStatus(Object data, String status) {
		super();
		this.data = data;
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
