package org.example;

import org.example.entities.User;
import org.example.model.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        UserDao userDao = context.getBean(UserDao.class);
        User user = new User(1L, "user");
        userDao.save(user);
        user.setUsername("test");
        userDao.update(user);
        User userFromDatabase = userDao.getById(user.getId());
        userDao.delete(userFromDatabase.getId());
    }
}