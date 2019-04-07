package com.user.notes.service;

import com.user.notes.entity.UserAccount;

public interface UserAccountService {

    UserAccount save(UserAccount userAccount);

    UserAccount findByUsername(String username);

    String checkUserAccount(UserAccount userAccount);
}