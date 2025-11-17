package com.course.code.springbootweb01.service;

import java.util.List;
import com.course.code.springbootweb01.pojo.User;

public interface UserService {

    public List<User> findAll();

}