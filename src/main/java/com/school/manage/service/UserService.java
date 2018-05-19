package com.school.manage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.manage.dao.ResultDTO;
import com.school.manage.model.User;
import com.school.manage.model.Jurisdiction;
import com.school.manage.model.Subject;

public interface UserService {
	 public ResultDTO<User> register(User user);
	 
	 public ResultDTO<User> login(User user,HttpServletResponse response);
	 
	 public ResultDTO<User> islogin(HttpServletRequest request);
	 
	 public ResultDTO<List<User>> userInfo(User user);
	 
	 public ResultDTO<Jurisdiction> Jurisdiction(int jurisdictionId);
	 
	 public ResultDTO<User> changePwd(User user);
	 
	 public ResultDTO<User> update(User user);
	 
	 public ResultDTO<User> delete(User user);
	 
	 public ResultDTO<List<Subject>> subjectList();
	 
	 public  ResultDTO<Subject> updateSubject(Subject subject);
}
