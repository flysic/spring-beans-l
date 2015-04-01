package org.machine.spring_beans_l.service.impl;

import org.machine.spring_beans_l.entity.User;
import org.machine.spring_beans_l.service.UserService;
import org.springframework.util.Assert;

public class UserServiceImpl_m implements UserService {
	
	@Override
	public User find(String name) {
		Assert.notNull(name);
		User user = new User();
		user.setName(name+" -yong-");
		if (name.equals("shao")==true) {
			user.setAge(29);
		} else {
			user.setAge(23);
		}
		return user;
	}
}
