package com.user.notes.serviceTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.user.notes.entity.UserAccount;
import com.user.notes.repository.UserAccountRepositroy;
import com.user.notes.service.UserAccountServiceImpl;

public class UserAccountServiceTest {

    @Mock
    private UserAccountRepositroy userAccountRepository;

    private UserAccountServiceImpl userAccountService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userAccountService = new UserAccountServiceImpl(userAccountRepository);
    }

    @Test
    public void createUserAccountTest() {
        Mockito.when(userAccountRepository.save(mockUserAccount())).thenReturn(mockUserAccount());

        userAccountService.save(mockUserAccount());
    }

    @Test
    public void findByUsernameTest() {
        Mockito.when(userAccountRepository.findByUsername("test-user")).thenReturn(mockUserAccount());

        userAccountService.findByUsername("test-user");
    }

    @Test
    public void checkUserAccountTest() {
        Mockito.when(userAccountRepository.findByUsername("test-user")).thenReturn(mockUserAccount());
        Mockito.when(userAccountRepository.findAll()).thenReturn(new ArrayList<UserAccount>());

        userAccountService.checkUserAccount(mockUserAccount());
    }

    @Test
    public void checkUserAccountExistTest() {

        Mockito.when(userAccountRepository.findByUsername("test-user")).thenReturn(mockUserAccount());
        Mockito.when(userAccountRepository.findAll()).thenReturn(Collections.singletonList(mockUserAccount()));

        String result = userAccountService.checkUserAccount(mockUserAccount());

        assertEquals("User Already exist", result);
    }

    @Test
    public void checkUserAccountEmailTest() {

        Mockito.when(userAccountRepository.findByUsername("test-user")).thenReturn(null);
        Mockito.when(userAccountRepository.findAll()).thenReturn(Collections.singletonList(mockUserAccount()));

        String result = userAccountService.checkUserAccount(mockUserAccount());

        assertEquals("Entered mail is already registered", result);
    }

    public UserAccount mockUserAccount() {

        UserAccount userAccount = new UserAccount();

        userAccount.setFullName("User");
        userAccount.setUsername("test-user");
        userAccount.setPassword("user-password");
        userAccount.setConfirmPassword("user-password");
        userAccount.setEmailId("user@test.com");
        userAccount.setMobileNumber("654379835");
        return userAccount;
    }

}
