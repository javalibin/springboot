package com.school.manage.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.manage.dao.ResultDTO;
import com.school.manage.enums.UserEnum;
import com.school.manage.mapper.UserMapper;
import com.school.manage.model.User;
import com.school.manage.service.UserService;
import com.school.manage.util.CookieUtils;
import com.school.manage.util.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@	Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper  userDAO;
	
	 @Autowired
	 private RedisUtil redisUtil;

	@Override
	public ResultDTO<User> register(User user) {
		ResultDTO<User> result = new ResultDTO<User>();
		List<String> uncheck = new ArrayList<String>();
		uncheck.add(UserEnum.ID.getMsg());
		uncheck.add(UserEnum.GMTCREATE.getMsg());
		uncheck.add(UserEnum.GMTMODIFY.getMsg());
		String Flag = checkParam(user,uncheck);
		if(StringUtils.isNotBlank(Flag)) {
			return result.fail(Flag);
		}
		try {
			userDAO.insert(user);
		}catch (Exception e) {
			log.error(e.getMessage());
			return result.fail(UserEnum.BLANK.getMsg());
		}
		return result.success(UserEnum.SUCCESS.getMsg());
	}
	
	private String checkParam(Object obj,List<String> uncheck) {
		for(Field f : obj.getClass().getDeclaredFields()){
		     f.setAccessible(true);
		     log.info(f.getName());
		     try {
		    	if(!uncheck.contains(f.getName())){
					if(f.get(obj) == null) {
						return f.getName()+"不能为空";
					}
		    	}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}

	@Override
	public ResultDTO<User> login(User user,HttpServletResponse response) {
		ResultDTO<User> result = new ResultDTO<User>();
		String userName = user.getName();
		String pwd = user.getPwd();
		if(StringUtils.isBlank(userName)) {
			return result.fail(UserEnum.NAMEISNOTNULL.getMsg());
		}
		if(StringUtils.isBlank(pwd)) {
			return result.fail(UserEnum.PWDISNOTNULL.getMsg());
		}
		User userInfo = userDAO.selectByName(userName);
		if(null == userInfo) {
			return result.fail(UserEnum.USERDOESNOTEXIST.getMsg());
		}
		if(!pwd.equals(userInfo.getPwd())) {
			return result.fail(UserEnum.PWDERROR.getMsg());
		}
		//这里的cokkie暂时写死,不做高安全级别控制
		CookieUtils.writeCookie(response, "loginstatus", userName);
		try {
			redisUtil.set(userName, UserEnum.LOGINSTATUS.getMsg(),2*60*60L);
			log.info(redisUtil.get(userName).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.success(UserEnum.LOGINSUCCESS.getMsg());
	}
	
}
