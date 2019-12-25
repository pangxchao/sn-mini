package com.mini.core.web.test.service.impl;

import com.mini.core.web.test.dao.UserDao;
import com.mini.core.web.test.service.UserService;
import com.mini.core.web.test.dao.UserDao;
import com.mini.core.web.test.service.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * UserServiceImpl.java
 * @author xchao
 */
@Singleton
@Named("userService")
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	@Inject
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDao getUserDao() {
		return userDao;
	}
}