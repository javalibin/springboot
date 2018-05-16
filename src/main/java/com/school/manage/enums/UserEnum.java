package com.school.manage.enums;

public enum  UserEnum {
		SUCCESS("注册成功", 1), 
		FAIL("注册失败", 2),
		BLANK("网络异常", 3),
		NAMEISNOTNULL("用户名不能为空",4),
		PWDISNOTNULL("密码不能为空",5),
		USERDOESNOTEXIST("用户不存在",6),
		PWDERROR("密码错误",7),
		LOGINSUCCESS("登录成功",8),
		LOGINFAIL("登录失败",9),
		ID("id",10),
		GMTCREATE("gmtCreate",11),
		GMTMODIFY("gmtModify",12),
		LOGINSTATUS("success",13);
		private String msg;  
	    private int code;  
	    // 构造方法  
	    private UserEnum(String msg, int code) {  
	        this.msg = msg;  
	        this.code = code;  
	    }  
	    // 普通方法  
	    public static String getMsgByCode(int code) {  
	        for (UserEnum c : UserEnum.values()) {  
	            if (c.getCode() == code) {  
	                return c.msg;  
	            }  
	        }  
	        return null;  
	    }
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}  
	  
}
