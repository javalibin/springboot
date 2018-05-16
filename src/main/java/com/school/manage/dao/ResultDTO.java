package com.school.manage.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO<T> {
	private boolean success = true;
	private String code;
	private String msg;
	private T data;
	
	public ResultDTO<T> fail(String msg) {
		this.success = false;
		this.code = "500";
		this.msg = msg;
		return this;
	}
	public ResultDTO<T> fail(String code,String msg) {
		this.success = false;
		this.code = code;
		this.msg = msg;
		return this;
	}
	public ResultDTO<T> success(T t,String msg){
		this.success = true;
		this.code = "200";
		this.msg = msg;
		this.data = t;
		return this;
	}
	public ResultDTO<T> success(String msg){
		this.success = true;
		this.code = "200";
		this.msg = msg;
		return this;
	}
}
