package com.it.dao;

import com.it.domain.User;

import java.util.List;

public interface UserDao {
    public String Validation(String to,String username);
    public boolean Enroll(User user);
    public User FindByName(String username);
    public boolean FindStatus(User user);
    public int UpdateUser(User preUser,User newUser);
    public int findTotalCount();
    public List<User> findByPage(int start,int pageSize);
    public int DeleteUser(String username,String password);
    public int updateStatus(String username,String password,String status);
}
