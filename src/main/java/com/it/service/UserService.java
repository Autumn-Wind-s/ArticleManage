package com.it.service;

import com.it.domain.Article;
import com.it.domain.PageBean;
import com.it.domain.User;

public interface UserService {
    public String SendValidation(String to,String username);
    public boolean Enroll(User user);
    public String Login(User user);
    public User FindUser(User user);
    public boolean updateUser(User preUser, User newUser);
    public PageBean<User> pageQuery(int currentPage,int pageSize);
    public boolean DeleteUser(String username,String password);
    public boolean updateStatus(String username,String password,String status);
}
