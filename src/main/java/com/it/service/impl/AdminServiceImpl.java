package com.it.service.impl;

import com.it.dao.AdminDao;
import com.it.dao.impl.AdminDaoImpl;
import com.it.domain.Admin;
import com.it.service.AdminService;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public boolean Login(Admin admin) {
        Admin admin1 = adminDao.FindAdmin(admin);
        if (admin1 == null) {
            return false;
        } else return true;
    }

    @Override
    public boolean updateAdmin(Admin preAdmin, Admin newAdmin) {
        int i = adminDao.UpdateAdmin(preAdmin, newAdmin);
        if (i == 0) {
            return false;
        } else return true;
    }
}
