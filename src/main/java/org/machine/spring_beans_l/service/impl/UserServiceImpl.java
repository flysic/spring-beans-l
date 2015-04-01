package org.machine.spring_beans_l.service.impl;

import org.machine.spring_beans_l.entity.User;
import org.machine.spring_beans_l.service.UserService;
import org.springframework.util.Assert;

public class UserServiceImpl implements UserService {

	@Override
	public User find(String name) {
		Assert.notNull(name);
		User user = new User();
		user.setName(name);
		if (name.equals("shao")==true) {
			user.setAge(42);
		} else if(name.equals("liu")==true) {
			user.setAge(36);
		} else {
			user = null;
		}
		return user;
	}

}
