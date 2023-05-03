package com.dataentry.api.response;

public class ResponseData {
	private boolean error;
	private String errorMsg;
	private Object data;

	public ResponseData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseData(boolean error, String errorMsg, Object data) {
		super();
		this.error = error;
		this.errorMsg = errorMsg;
		this.data = data;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
