package com.it.service.impl;

import com.it.dao.UserDao;
import com.it.dao.impl.UserDaoImpl;
import com.it.domain.PageBean;
import com.it.domain.User;
import com.it.service.UserService;

import java.util.Objects;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public String SendValidation(String to, String username) {
        return userDao.Validation(to, username);
    }

    @Override
    public boolean Enroll(User user) {
        User u = userDao.FindByName(user.getUsername());
        if (u != null) {
            return false;
        } else {
            return userDao.Enroll(user);
        }

    }

    public User FindUser(User user) {
        return userDao.FindByName(user.getUsername());
    }

    @Override
    public String Login(User user) {
        User u = userDao.FindByName(user.getUsername());
        if (u != null) {
            if (Objects.equals(u.getPassword(), user.getPassword())) {
                if (u.getStatus().equals("正常")) {
                    return "登录成功";
                } else {
                    return "账号已冻结";
                }
            } else {
                return "密码错误";
            }
        } else
            return "用户名不存在";
    }

    public boolean updateUser(User preUser, User newUser) {
        int i = userDao.UpdateUser(preUser, newUser);
        if (i == 0) {
            return false;
        } else return true;


    }

    @Override
    public PageBean<User> pageQuery(int currentPage, int pageSize) {
        PageBean<User> pb = new PageBean<>();
        int start = (currentPage - 1) * pageSize;
        int totalPage;
        if (userDao.findTotalCount() == 0) {
            totalPage = 0;
        } else {
            totalPage = userDao.findTotalCount() % pageSize == 0 ? userDao.findTotalCount() / pageSize : (userDao.findTotalCount() / pageSize) + 1;
        }
        pb.setCurrentPage(currentPage);
        pb.setTotalCount(userDao.findTotalCount());
        pb.setList(userDao.findByPage(start, pageSize));
        pb.setTotalPage(totalPage);
        pb.setPageSize(pageSize);
        return pb;
    }

    @Override
    public boolean DeleteUser(String username, String password) {
        int i = userDao.DeleteUser(username, password);
        if (i == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean updateStatus(String username, String password,String status) {
        int i = userDao.updateStatus(username, password, status);
        if (i==0){
            return false;
        }
        else return true;

    }


}
