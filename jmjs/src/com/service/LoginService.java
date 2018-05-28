package com.service;

import com.dao.LoginDao;
import com.pojo.UsersDTO;

public class LoginService {
	private LoginDao dao = new LoginDao();

	public UsersDTO login(UsersDTO user) {
		return dao.login(user);
	}

	public UsersDTO queryAllPromission(UsersDTO user) {
		// TODO Auto-generated method stub
		return dao.queryAllPromission(user);
	}
	
}
