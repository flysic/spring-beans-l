package org.machine.spring_beans_l;

import org.machine.spring_beans_l.entity.User;
import org.machine.spring_beans_l.service.UserService;
import org.machine.spring_beans_l.service.impl.UserServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

@SpringBootApplication
public class DefaultListableBeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefaultListableBeanApplication.class, args);
		ClassPathResource resource = new ClassPathResource("applicationConfigure.xml");
		
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(resource);
		
		UserService userService = factory.getBean(UserService.class);
		Assert.notNull(userService);
		User user = userService.find("shao");
		BeanDefinition beanDefinition = factory.getMergedBeanDefinition("userService");
		System.out.println(beanDefinition.getBeanClassName());
		System.out.println(factory.isTypeMatch("userService", UserService.class));
		System.out.println(factory.isSingleton("userService"));
		System.out.println(factory.isPrototype("userService"));
		System.out.println(factory.getType("userService"));
		
		Assert.notNull(user);
		System.out.println(user.getName() + " " + user.getAge());
		
		System.out.println(factory.getBeanDefinitionNames().length);
	}

}
