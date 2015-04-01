package org.machine.spring_beans_l;

import org.machine.spring_beans_l.entity.User;
import org.machine.spring_beans_l.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class XmlBeanApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmlBeanApplication.class, args);
        ClassPathResource resource = new ClassPathResource("applicationConfigure.xml");
        
        BeanFactory factory = new XmlBeanFactory(resource);
        
        UserService userService = (UserService)factory.getBean("userService");
        User user = userService.find("shao");
        System.out.println(user.getName() + " " + user.getAge());
    }
}
