package com.school.manage.service;

import javax.servlet.http.HttpServletResponse;

import com.school.manage.dao.ResultDTO;
import com.school.manage.model.User;

public interface UserService {
	 public ResultDTO<User> register(User user);
	 
	 public ResultDTO<User> login(User user,HttpServletResponse response);
}
