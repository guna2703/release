package com.user.notes.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;
import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.user.notes.controller.LoginController;
import com.user.notes.entity.UserAccount;
import com.user.notes.entity.UserNote;
import com.user.notes.service.LoginService;
import com.user.notes.service.UserAccountService;
import com.user.notes.service.UserNoteService;

@Configuration
@EnableWebMvc
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LoginService loginService;

    @Mock
    private UserAccountService userAccountService;

    @Mock
    private UserNoteService userNoteService;

    @InjectMocks
    private LoginController loginController;

    @Before
    public void setUp() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");

        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(resolver).build();
    }

    @Test
    public void redirectLoginPageTest() throws Exception {

        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    public void LoginToUserNotePageTest() throws Exception {

        Mockito.doReturn(true).when(loginService).validateUser(any(String.class), any(String.class));
        Mockito.doReturn(new ArrayList<UserNote>()).when(userNoteService).findAllByUsername(any(String.class));

        mockMvc.perform(post("/login.html")
                .flashAttr("loginForm", mockUserAccount()))
        .andExpect(status().isOk())
        .andExpect(view().name("usernotes"))
        .andDo(print());
    }

    @Test
    public void redirectRegistrationPageTest() throws Exception {

        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andReturn();
    }

  @Test
  public void redirectGetRegistrationPageTest() throws Exception {

      mockMvc.perform(get("/registration"))
      .andExpect(status().isOk())
      .andReturn();
  }

    @Test
    public void checkRegistrationFailedTest() throws Exception {

        Mockito.doReturn("something").when(userAccountService).checkUserAccount(mockUserAccount());

        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                .content(mockUserAccountJson())
                .flashAttr("accountForm", mockUserAccount()))
        .andExpect(status().isOk())
        .andExpect(view().name("registration"))
        .andDo(print());
    }

    @Test
    public void checkRegistrationPassTest() throws Exception {

        Mockito.doReturn(null).when(userAccountService).checkUserAccount(mockUserAccount());
        Mockito.doReturn(mockUserAccount()).when(userAccountService).save(mockUserAccount());

        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                .content(mockUserAccountJson())
                .flashAttr("accountForm", mockUserAccount()))
        .andExpect(status().isOk())
        .andExpect(view().name("login"))
        .andDo(print());
    }

    @Test
    public void checkRegistrationPasswordMatchFailedTest() throws Exception {

        UserAccount account = mockUserAccount();
        account.setConfirmPassword("wrong");

        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                .content(mockUserAccountJson())
                .flashAttr("accountForm", account))
        .andExpect(status().isOk())
        .andExpect(view().name("registration"))
        .andDo(print());
    }

    @Test
    public void logoutUserTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/logout")
                .flashAttr("accountForm", mockUserAccount()))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/login?logout"));
    }

    public String mockUserAccountJson() {
        return new JSONObject(mockUserAccount()).toString();
    }


    public static UserAccount mockUserAccount() {

        UserAccount userAccount = new UserAccount();

        userAccount.setId(123l);
        userAccount.setFullName("User");
        userAccount.setUsername("test-user");
        userAccount.setPassword("user-password");
        userAccount.setDateOfBirth(new Date(123412342));
        userAccount.setConfirmPassword("user-password");
        userAccount.setEmailId("user@test.com");
        userAccount.setMobileNumber("654379835");
        return userAccount;
    }

}
