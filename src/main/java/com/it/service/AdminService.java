package com.it.service;

import com.it.domain.Admin;

public interface AdminService {
    public boolean Login(Admin admin);
    public boolean updateAdmin(Admin preAdmin,Admin newAdmin);
}
