package com.example.demo.modules.common.vo;

public class Result<T> {
	
	private int status;
	private String message;
	private T objiect;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getObjiect() {
		return objiect;
	}
	public void setObjiect(T objiect) {
		this.objiect = objiect;
	}
	
	
	
	public Result() {
		super();
	}
	public Result(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public Result(int status, String message, T objiect) {
		super();
		this.status = status;
		this.message = message;
		this.objiect = objiect;
	}



	public enum ResultStatus{
		SUCCESS(200),FAILD(500);
		public int status;
		private ResultStatus(int status){
			this.status= status;
		}
	}
	
	
	
}
