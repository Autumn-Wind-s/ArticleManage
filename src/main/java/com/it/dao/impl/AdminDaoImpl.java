package com.it.dao.impl;

import com.it.dao.AdminDao;
import com.it.domain.Admin;
import com.it.util.JdbcUtils;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class AdminDaoImpl implements AdminDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDs());

    @Override
    public Admin FindAdmin(Admin admin) {
        String sql = "select * from admin where username=? and password =?";
        Admin admin1 = null;
        try {
            admin1 = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), admin.getUsername(), admin.getPassword());
        } catch (DataAccessException e) {
            return null;
        }

        return admin1;
    }

    @Override
    public int UpdateAdmin(Admin preAdmin, Admin newAdmin) {
        String sql = "update admin set username= ? ,password= ? where username=? and password =?";
        try {
            return template.update(sql, newAdmin.getUsername(), newAdmin.getPassword(), preAdmin.getUsername(), preAdmin.getPassword());
        } catch (DataAccessException e) {

            return 0;
        }

    }

    @Test
    public void test() {
        String sql = "select * from admin where username=? and password =?";
        Admin admin = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), "song", "123");
        System.out.println(admin.getPassword() + admin.getUsername());
    }
}
