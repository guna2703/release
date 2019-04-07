package com.user.notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.notes.entity.UserAccount;

@Service
public class LoginService {

    @Autowired
    private UserAccountService accountService;

    public boolean validateUser(String username, String password) {

        UserAccount account = accountService.findByUsername(username);
        if (account != null) {
            return username.equalsIgnoreCase(account.getUsername())
                    && password.equalsIgnoreCase(account.getPassword());
        }
        return false;
    }
}