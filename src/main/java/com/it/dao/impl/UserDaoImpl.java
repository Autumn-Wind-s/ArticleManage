package com.it.dao.impl;

import org.junit.Test;
import com.it.dao.UserDao;
import com.it.domain.User;
import com.it.util.JdbcUtils;
import com.it.util.MailUtils;
import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDs());

    @Override
    public String Validation(String to, String username) {

        String random = RandomStringUtils.randomAlphabetic(5);
        String text = "尊敬的" + username + "用户您好，您正在进行文章管理系统的注册，以下是您的验证码：" + random + "。请保管好您的个人信息，并在五分钟之内完成注册。如非本人操作，请忽略。";
        boolean flag = MailUtils.sendMail(to, text, "文章管理系统注册验证");
        if (flag) {
            return random;
        } else {
            return "邮箱错误";
        }
    }

    @Override
    public boolean Enroll(User user) {
        String sql = "insert into user(username,password,email) values(?,?,?)";
        int update = 0;
        try {
            update = template.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if (update == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public User FindByName(String username) throws EmptyResultDataAccessException {
        User user = null;

        String sql = "select * from user where username= ?  ";
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }

    @Override
    public boolean FindStatus(User user) {
        return false;
    }

    public int UpdateUser(User preUser, User newUser) {
        String sql = "update user set username=?,password=?,email=? where username=? and password=? and email=?";
        int update = 0;
        try {
            update = template.update(sql, newUser.getUsername(), newUser.getPassword(), newUser.getEmail(), preUser.getUsername(), preUser.getPassword(), preUser.getEmail());
        } catch (DataAccessException e) {
            return update;
        }
        return update;
    }

    @Override
    public int findTotalCount() {
        String sql="select count(*)  from user";
        int i = 0;
        try {
            i = template.queryForObject(sql, Integer.class);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<User> findByPage(int start, int pageSize) {
      String sql="select * from user limit ?,?";
        try {
            return template.query(sql,new BeanPropertyRowMapper<User>(User.class),start,pageSize);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public int DeleteUser(String username, String password) {
        String sql="delete from user where username=? and password=?";
        try {
            int update = template.update(sql, username, password);
        } catch (DataAccessException e) {
           return 0;
        }
        return 1;
    }

    @Override
    public int updateStatus(String username, String password,String status) {
        String sql="update user set status=? where username=? and password=?";
        int update;
        try {
             update = template.update(sql, status, username, password);
        } catch (DataAccessException e) {
            return  0;
        }
        return update;
    }

    @Test
    public void test() {
        String sql="select count(*)  from user";
        int i = template.queryForObject(sql, Integer.class);
        System.out.println(i);
    }
}
