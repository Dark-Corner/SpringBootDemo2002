package com.example.demo.modules.account.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.modules.account.dao.UserDao;
import com.example.demo.modules.account.dao.UserRoleDao;
import com.example.demo.modules.account.entity.Role;
import com.example.demo.modules.account.entity.User;
import com.example.demo.modules.account.service.UserService;
import com.example.demo.modules.common.vo.Result;
import com.example.demo.modules.common.vo.Result.ResultStatus;
import com.example.demo.modules.common.vo.SearchVo;
import com.example.demo.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	@Transactional
	public Result<User> insertUser(User user) {
		Result<User> result = new Result<User>(ResultStatus.SUCCESS.status, "");
		
		User userTemp = userDao.getUserByUserName(user.getUserName());
		if (userTemp != null) {
			result.setStatus(ResultStatus.FAILD.status);
			result.setMessage("User name is repeat.");
			return result;
		}
		
		user.setCreateDate(new Date());
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		userDao.insertUser(user);
		
		List<Role> roles = user.getRoles();
		if (roles != null) {
			userRoleDao.deletUserRoleByUserId(user.getUserId());
			for (Role role : roles) {
				userRoleDao.addUserRole(role.getRoleId(), user.getUserId());
			}
		}
		
		try {
//			Subject subject = SecurityUtils.getSubject();
//			UsernamePasswordToken usernamePasswordToken = 
//					new UsernamePasswordToken(user.getUserName(), user.getPassword());
//			
//			subject.login(usernamePasswordToken);
//			subject.checkRoles();
		} catch (Exception e) {
			result.setStatus(ResultStatus.FAILD.status);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Override
	public Result<User> login(User user) {
		Result<User> result = new Result<User>(ResultStatus.SUCCESS.status, "");
		
		User userTemp = userDao.getUserByUserName(user.getUserName());
		if (userTemp == null || !userTemp.getPassword().equals(MD5Util.getMD5(user.getPassword()))) {
			result.setStatus(ResultStatus.FAILD.status);
			result.setMessage("User name or password error.");
		}
		
		try {
//			Subject subject = SecurityUtils.getSubject();
//			UsernamePasswordToken usernamePasswordToken = 
//					new UsernamePasswordToken(user.getUserName(), MD5Util.getMD5(user.getPassword()));
//			usernamePasswordToken.setRememberMe(user.getRememberMe());
//			
//			subject.login(usernamePasswordToken);
//			subject.checkRoles();
		} catch (Exception e) {
			result.setStatus(ResultStatus.FAILD.status);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

	@Override
	public void logout() {
//		Subject subject = SecurityUtils.getSubject();
//		subject.logout();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
		searchVo.initSearchVo(searchVo);
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo(
				Optional.ofNullable(userDao.getUsersBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
	}

	@Override
	public User getUserById(int userId) {
		User userById = userDao.getUserById(userId);
		userById.setPassword(MD5Util.getMD5(MD5Util.getMD5(userById.getPassword())));
		return userById;
	}

	@Override
	@Transactional
	public Result<User> updateUser(User user) {
		
		User userTemp = userDao.getUserByUserName(user.getUserName());
		if (userTemp != null && user.getUserId() != userTemp.getUserId()) {
			return new Result<User>(ResultStatus.FAILD.status, "User name is repeat.");
		}
		
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		userDao.updateUser(user);
		List<Role> roles = user.getRoles();
		if (!roles.isEmpty()) {
			userRoleDao.deletUserRoleByUserId(user.getUserId());
			for (Role role : roles) {
				userRoleDao.addUserRole(role.getRoleId(), user.getUserId());
			}
		}
		
		return new Result<User>(ResultStatus.SUCCESS.status, "");
	}

	@Override
	@Transactional
	public Result<User> deleteUser(int userId) {
		userDao.deleteUser(userId);
		return new Result<User>(ResultStatus.SUCCESS.status, "");
	}
}
