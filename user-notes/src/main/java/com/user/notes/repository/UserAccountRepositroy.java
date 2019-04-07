package com.user.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.notes.entity.UserAccount;

@Repository
public interface UserAccountRepositroy extends JpaRepository<UserAccount, Long> {

    UserAccount findByUsername(String username);
}
