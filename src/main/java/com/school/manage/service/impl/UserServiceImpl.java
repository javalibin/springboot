package com.school.manage.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.school.manage.dao.ResultDTO;
import com.school.manage.enums.UserEnum;
import com.school.manage.mapper.JurisdictionMapper;
import com.school.manage.mapper.SubjectMapper;
import com.school.manage.mapper.UserMapper;
import com.school.manage.model.User;
import com.school.manage.service.UserService;
import com.school.manage.util.CookieUtils;
import com.school.manage.util.RedisUtil;
import com.school.manage.model.Jurisdiction;
import com.school.manage.model.Subject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@	Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper  userDAO;
	
	@Autowired
	private JurisdictionMapper  jurisdictionDAO;
	
	@Autowired
	private SubjectMapper  subjectDAO;
	
	 @Autowired
	 private RedisUtil redisUtil;

	@Override
	public ResultDTO<User> register(User user) {
		ResultDTO<User> result = new ResultDTO<User>();
		List<String> uncheck = new ArrayList<String>();
		uncheck.add(UserEnum.ID.getMsg());
		uncheck.add(UserEnum.GMTCREATE.getMsg());
		uncheck.add(UserEnum.GMTMODIFY.getMsg());
		uncheck.add("newPwd");
		uncheck.add(UserEnum.SEX.getMsg());
		String Flag = checkParam(user,uncheck);
		if(StringUtils.isNotBlank(Flag)) {
			return result.fail(Flag);
		}
		User users = userDAO.selectByName(user.getName());
		if(null != users) {
			return result.fail("用户已存在");
		}
		try {
			userDAO.insertSelective(user);
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
			userInfo.setAge(null);
			userInfo.setBirthday(null);
			userInfo.setGmtCreate(null);
			userInfo.setGmtModify(null);
			userInfo.setPwd(null);
			redisUtil.set(userName, JSON.toJSONString(userInfo),2*60*60L);
			log.info(redisUtil.get(userName).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.success(UserEnum.LOGINSUCCESS.getMsg());
	}

	@Override
	public ResultDTO<User> islogin(HttpServletRequest request) {
		ResultDTO<User> result = new ResultDTO<User>();
		String value =  CookieUtils.getCookie(request, "loginstatus");
		if(StringUtils.isBlank(value)) {
			return result.fail("用户未登录");
		}
		Object results = redisUtil.get(value);
		if(results == null) {
			return result.fail("用户登录信息不存在");
		}
		return result.success(String.valueOf(results));
	}

	@Override
	public ResultDTO<List<User>> userInfo(User user) {
		ResultDTO<List<User>> result = new ResultDTO<List<User>>();
		List<User> userList = new ArrayList<User>();
		if(StringUtils.isBlank(user.getName())) {
			userList = userDAO.selectAll();
		}else {
			User users = userDAO.selectByName(user.getName());
			if(null == users) {
				return result.fail("用户信息不存在");
			}
			userList.add(users);
		}
		return result.success(userList, UserEnum.LOGINSUCCESS.getMsg());
	}

	@Override
	public ResultDTO<Jurisdiction> Jurisdiction(int jurisdictionId) {
		ResultDTO<Jurisdiction> result = new ResultDTO<Jurisdiction>();
		Jurisdiction jurisdiction =  jurisdictionDAO.selectByPrimaryKey(jurisdictionId);
		return result.success(jurisdiction,UserEnum.LOGINSUCCESS.getMsg());
	}

	@Override
	public ResultDTO<User> changePwd(User user) {
		ResultDTO<User> result = new ResultDTO<User>();
		User resultUser = userDAO.selectById(user.getId().toString());
		if(!user.getPwd().equals(resultUser.getPwd())) {
			return result.fail("原密码错误");
		}
		userDAO.updateByPrimaryKeySelective(user);
		return result.success(UserEnum.LOGINSUCCESS.getMsg());
	}

	@Override
	public ResultDTO<User> update(User user) {
		ResultDTO<User> result = new ResultDTO<User>();
		if(user.getPwd().length() < 6) {
			return result.fail("密码不能少于6位");
		}
		userDAO.updateByPrimaryKeySelective(user);
		return result.success(UserEnum.LOGINSUCCESS.getMsg());
	}

	@Override
	public ResultDTO<User> delete(User user) {
		ResultDTO<User> result = new ResultDTO<User>();
		userDAO.deleteByPrimaryKey(user.getId());
		return result.success(UserEnum.LOGINSUCCESS.getMsg());
	}

	@Override
	public ResultDTO<List<Subject>> subjectList() {
		ResultDTO<List<Subject>> result = new ResultDTO<List<Subject>>();
		List<Subject> list = subjectDAO.selectAll();
		return result.success(list,UserEnum.LOGINSUCCESS.getMsg());
	}

	@Override
	public ResultDTO<Subject> updateSubject(Subject subject) {
		ResultDTO<Subject> resuls = new ResultDTO<Subject>();
		Subject result =  subjectDAO.selectByRowCloun(subject);
		if(null == result) {
			subjectDAO.insert(subject);
			resuls.success("新增课程成功");
		}else {
			subjectDAO.updateByRowCloun(subject);
			resuls.success("修改课程成功");
		}
		return resuls;
	}
	
}
