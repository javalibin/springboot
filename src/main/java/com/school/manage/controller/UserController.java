package com.school.manage.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.school.manage.dao.ResultDTO;
import com.school.manage.enums.UserEnum;
import com.school.manage.model.User;
import com.school.manage.service.UserService;
import com.school.manage.util.CookieUtils;
import com.school.manage.util.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {
	
		@Autowired 
		private UserService userService;
		
		 @Autowired
		 private RedisUtil redisUtil;
	
		@RequestMapping(value = "/register", method = RequestMethod.POST)
	    @ResponseBody
		public ResultDTO<User> register(User user) {
			log.info("请求参数USER:{}",JSON.toJSONString(user));
			return userService.register(user);
		}
		
		@RequestMapping(value ="/login", method = RequestMethod.POST)
	    @ResponseBody
		public ResultDTO<User> login(User user,HttpServletResponse response ) {
			log.info("请求参数USER:{}",JSON.toJSONString(user));
			return userService.login(user,response);
		}
		
		@RequestMapping(value ="/list", method = RequestMethod.POST)
	    @ResponseBody
		public ResultDTO<List<User>> userInfo(){
			List<User> list = new ArrayList<User>();
			User user = new User();
			user.setAge(18);
			user.setBirthday("1996-11-20");
			user.setJurisdictionId(0);
			user.setName("我的个乖乖");
			user.setPwd("a111111");
			user.setSex("男");
			list.add(user);
			ResultDTO<List<User>> result = new ResultDTO<List<User>>();
			result.setData(list);
			return result.success(list,UserEnum.LOGINSTATUS.getMsg());
		}
		
		@RequestMapping(value ="/islogin", method = RequestMethod.POST)
	    @ResponseBody
		public ResultDTO<User> islogin(HttpServletRequest request) {
			log.info("请求是否登录接口");
			ResultDTO<User> result = new ResultDTO<User>();
			String value =  CookieUtils.getCookie(request, "loginstatus");
			if(StringUtils.isBlank(value)) {
				return result.fail("用户未登录");
			}
			Object results = redisUtil.get(value);
			if(results == null) {
				return result.fail("用户登录信息不存在");
			}
			log.info("状态吗:"+UserEnum.LOGINSTATUS.getMsg());
			log.info("result:"+results.toString());
			if(!UserEnum.LOGINSTATUS.getMsg().equals(String.valueOf(results))) {
				return result.fail("用户登录信息不存在");
			}
			return result.success(value);
		}
		
}
