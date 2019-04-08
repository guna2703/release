//package com.user.notes.controllertest;
//
//import org.json.JSONObject;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.user.notes.controller.LoginController;
//import com.user.notes.entity.UserAccount;
//import com.user.notes.service.LoginService;
//import com.user.notes.service.UserAccountService;
//import com.user.notes.service.UserNoteService;
//
//@ActiveProfiles("test")
//@RunWith(SpringJUnit4ClassRunner.class)
//public class LoginControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private LoginService loginService;
//
//    @Mock
//    private UserAccountService userAccountService;
//
//    @Mock
//    private UserNoteService userNoteService;
//
//    @InjectMocks
//    private LoginController controller;
//
//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//    }
//
//    @Test
//    public void redirectLoginPageTest() throws Exception {
//
//        mockMvc.perform(get("/login"))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//
//    @Test
//    public void redirectRegistrationPageTest() throws Exception {
//
//        mockMvc.perform(get("/registration"))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//  @Test
//  public void redirectCreateRegistrationPageTest() throws Exception {
//
//      Mockito.when(userAccountService.checkUserAccount(mockLoginUserAccount())).thenReturn(null);
//
//      String userAccountJson = new JSONObject(mockLoginUserAccount()).toString();
//
//      mockMvc.perform(post("/registration")
//              .content(userAccountJson))
//              .andExpect(status().isOk())
//              .andReturn();
//  }
//
//
//    public UserAccount mockLoginUserAccount() {
//
//        UserAccount userAccount = new UserAccount();
//
//        userAccount.setFullName("User");
//        userAccount.setUsername("test-user");
//        userAccount.setPassword("user-password");
//        userAccount.setConfirmPassword("user-password");
//        userAccount.setEmailId("user@test.com");
//        userAccount.setMobileNumber("654379835");
//        return userAccount;
//    }
//
//}
