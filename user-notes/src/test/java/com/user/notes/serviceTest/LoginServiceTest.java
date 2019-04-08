package com.user.notes.serviceTest;

import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.user.notes.entity.UserAccount;
import com.user.notes.service.LoginService;
import com.user.notes.service.UserAccountServiceImpl;

public class LoginServiceTest {

    @Mock
    private UserAccountServiceImpl userAccountService;

    @InjectMocks
    private LoginService loginService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void valildateUserTest() {
       doReturn(mockUserAccount()).when(userAccountService).findByUsername("test-user");
       loginService.validateUser("test-user", "user-password");

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
