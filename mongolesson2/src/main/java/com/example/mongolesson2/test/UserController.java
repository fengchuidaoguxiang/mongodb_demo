package com.example.mongolesson2.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserDAO userDAO;

    @GetMapping("/save")
    public void save(){
        User user = new User(11,"test",33);
        userDAO.save(user);
    }

    @GetMapping("/find")
    public User findOne(){
        User user = userDAO.findByName("test");
        System.out.println(user);
        return user;
    }

    @GetMapping("/update")
    public void update(){
        User user = new User(11,"Tomdd", 33);
        userDAO.update(user);
    }

    @GetMapping("/delete")
    public void delete(){
        userDAO.delete(11);
    }
}
