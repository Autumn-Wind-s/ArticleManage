package com.it.dao;

import com.it.domain.Admin;
import com.it.domain.User;

public interface AdminDao {
    public Admin FindAdmin(Admin admin);
    public  int UpdateAdmin(Admin preAdmin,Admin newAdmin);
}
