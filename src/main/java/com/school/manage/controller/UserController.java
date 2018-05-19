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
import com.school.manage.model.Jurisdiction;
import com.school.manage.model.Subject;

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
		public ResultDTO<List<User>> userInfo(User user){
			return userService.userInfo(user);
		}
		
		@RequestMapping(value ="/islogin", method = RequestMethod.POST)
	    @ResponseBody
		public ResultDTO<User> islogin(HttpServletRequest request) {
			log.info("请求是否登录接口");
			return userService.islogin(request);
		}
		
		@RequestMapping(value ="/Jurisdiction", method = RequestMethod.POST)
	    @ResponseBody
		public ResultDTO<Jurisdiction> Jurisdiction(int jurisdictionId) {
			return userService.Jurisdiction(jurisdictionId);
		}
		
		@RequestMapping(value ="/changePwd", method = RequestMethod.POST)
	    @ResponseBody
		public ResultDTO<User> changePwd(User user) {
			return userService.changePwd(user);
		}
		
		@RequestMapping(value ="/update", method = RequestMethod.POST)
	    @ResponseBody
		public ResultDTO<User> update(User user) {
			return userService.update(user);
		}
		
		@RequestMapping(value ="/delete", method = RequestMethod.POST)
	    @ResponseBody
		public ResultDTO<User> delete(User user) {
			return userService.delete(user);
		}
		
		@RequestMapping(value ="/subjectList", method = RequestMethod.POST)
	    @ResponseBody
		public ResultDTO<List<Subject>> subjectList() {
			return userService.subjectList();
		}
		
		@RequestMapping(value ="/updateSubject", method = RequestMethod.POST)
	    @ResponseBody
		public ResultDTO<Subject> updateSubject(Subject subject) {
			return userService.updateSubject(subject);
		}
}
