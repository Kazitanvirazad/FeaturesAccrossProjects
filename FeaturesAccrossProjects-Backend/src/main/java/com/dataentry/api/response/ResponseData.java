package com.dataentry.api.response;

public class ResponseData {
	private boolean error;
	private String message;
	private Object data;

	public ResponseData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseData(boolean error, String message) {
		super();
		this.error = error;
		this.message = message;
	}

	public ResponseData(boolean error, String message, Object data) {
		super();
		this.error = error;
		this.data = data;
		this.message = message;
	}

	public ResponseData(boolean error, Object data) {
		super();
		this.error = error;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
