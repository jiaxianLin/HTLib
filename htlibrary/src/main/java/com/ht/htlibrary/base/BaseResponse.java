package com.ht.htlibrary.base;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class BaseResponse<T> {

	String msg;

	String code;

	T result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
