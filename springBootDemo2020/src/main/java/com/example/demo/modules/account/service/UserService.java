package com.example.demo.modules.account.service;

import com.example.demo.modules.account.entity.User;
import com.example.demo.modules.common.vo.Result;
import com.example.demo.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;

public interface UserService {
	
	Result<User> insertUser(User user);
	
	User getUserByUserName(String userName);
	
	Result<User> login(User user);
	
	void logout();
	
	PageInfo<User> getUsersBySearchVo(SearchVo searchVo);
	
	User getUserById(int userId);
	
	Result<User> updateUser(User user);
	
	Result<User> deleteUser(int userId);
}
