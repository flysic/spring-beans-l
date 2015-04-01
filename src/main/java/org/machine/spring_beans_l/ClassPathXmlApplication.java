package org.machine.spring_beans_l;

import org.machine.spring_beans_l.entity.User;
import org.machine.spring_beans_l.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ClassPathXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassPathXmlApplication.class, args);
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/applicationConfigure.xml");
		UserService userService = (UserService)context.getBean("userService");
		User user = userService.find("shao");
		System.out.println(user.getName() + " " + user.getAge());
		context.close();
	}
}
