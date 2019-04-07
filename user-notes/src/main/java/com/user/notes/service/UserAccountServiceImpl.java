package com.user.notes.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.notes.entity.UserAccount;
import com.user.notes.repository.UserAccountRepositroy;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepositroy userAccountRepositroy;

    public UserAccountServiceImpl(UserAccountRepositroy userAccountRepositroy) {
        this.userAccountRepositroy = userAccountRepositroy;
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        return userAccountRepositroy.save(userAccount);
    }

    @Override
    public UserAccount findByUsername(String username) {
        return userAccountRepositroy.findByUsername(username);
    }

    @Override
    public String checkUserAccount(UserAccount userAccount) {

        UserAccount savedUserAccount = findByUsername(userAccount.getUsername());
        if (savedUserAccount != null) {
            return "User Already exist";
        } else {
            List<UserAccount> UserAccounts = userAccountRepositroy.findAll();

            Set<String> registeredMails = UserAccounts.stream().map(UserAccount::getEmailId).collect(Collectors.toSet());

            if (registeredMails.contains(userAccount.getEmailId())) {
                return "Entered mail is already registered";
            }
        }
        return null;
    }

}
