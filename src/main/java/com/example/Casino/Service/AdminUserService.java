package com.example.Casino.Service;


import com.example.Casino.Model.AdminUser;
import com.example.Casino.Model.User;import com.example.Casino.Repo.AdminUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    @Autowired
    private AdminUserRepository adminUserRepository;

    public AdminUser authenticate(String username, String password) {
        AdminUser adminUser = adminUserRepository.findByUsername(username);
        if (adminUser != null && adminUser.getPassword().equals(password)) {
            return adminUser;
        }
        return null;
    }
}
