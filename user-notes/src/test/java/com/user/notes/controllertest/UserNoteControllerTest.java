package com.user.notes.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Date;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.user.notes.controller.UserNoteController;
import com.user.notes.entity.UserAccount;
import com.user.notes.entity.UserNote;
import com.user.notes.service.UserAccountService;
import com.user.notes.service.UserNoteService;

@Configuration
@EnableWebMvc
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UserNoteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserAccountService userAccountService;

    @Mock
    private UserNoteService userNoteService;

    @InjectMocks
    private UserNoteController userNoteController;

    @Before
    public void setUp() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");

        this.mockMvc = MockMvcBuilders.standaloneSetup(userNoteController).setViewResolvers(resolver).build();
    }

    @Test
    public void redirectLoginPageTest() throws Exception {

        Mockito.doReturn(LoginControllerTest.mockUserAccount()).when(userAccountService).findByUsername(any(String.class));
        Mockito.doReturn(mockUserNote()).when(userNoteService).createUserNote(mockUserNote());
        Mockito.doReturn(new ArrayList<>()).when(userNoteService).findAllByUsername(any(String.class));

        mockMvc.perform(post("/usernotes")
                .flashAttr("userNoteForm", mockUserNote()))
        .andExpect(status().isOk())
        .andExpect(view().name("usernotes"))
        .andDo(print());
    }

    @Test
    public void editUserNote() throws Exception {
        Mockito.doReturn(mockUserNote()).when(userNoteService).findById(123l);

        mockMvc.perform(get("/edit/123")
                .flashAttr("userNoteForm", mockUserNote()))
        .andExpect(status().isOk())
        .andExpect(view().name("usernoteAction"))
        .andDo(print());
    }

    @Test
    public void viewUserNote() throws Exception {
        Mockito.doReturn(mockUserNote()).when(userNoteService).findById(123l);

        mockMvc.perform(get("/view/123")
                .flashAttr("userNoteForm", mockUserNote()))
        .andExpect(status().isOk())
        .andExpect(view().name("usernoteAction"))
        .andDo(print());
    }

    @Test
    public void updateUserNote() throws Exception {
        Mockito.doReturn(mockUserNote()).when(userNoteService).findById(123l);
        Mockito.doReturn(mockUserNote()).when(userNoteService).update(mockUserNote());
        Mockito.doReturn(new ArrayList<>()).when(userNoteService).findAllByUsername(any(String.class));

        mockMvc.perform(post("/update/123")
                .flashAttr("userNote", mockUserNote()))
        .andExpect(status().isOk())
        .andExpect(view().name("usernotes"))
        .andDo(print());
    }

    @Test
    public void deleteUserNote() throws Exception {
        Mockito.doReturn(mockUserNote()).when(userNoteService).findById(123l);
        Mockito.doNothing().when(userNoteService).delete(mockUserNote());
        Mockito.doReturn(new ArrayList<>()).when(userNoteService).findAllByUsername(any(String.class));

        mockMvc.perform(get("/delete/123")
                .flashAttr("userNote", mockUserNote()))
        .andExpect(status().isOk())
        .andExpect(view().name("usernotes"))
        .andDo(print());
    }

    private UserNote mockUserNote() {

        UserNote note = new UserNote();

        note.setId(123l);
        note.setTitle("test-title");
        note.setUserAccount(new UserAccount());
        note.setCategory("test-category");
        note.setUsername("test-username");
        note.setCreatedTime(new Date());
        note.setUpdatedTime(new Date());

        return note;
    }

}
