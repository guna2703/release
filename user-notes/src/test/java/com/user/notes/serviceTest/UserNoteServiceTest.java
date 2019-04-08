package com.user.notes.serviceTest;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.user.notes.entity.UserNote;
import com.user.notes.repository.UserNoteRepository;
import com.user.notes.service.UserNoteServiceImpl;

public class UserNoteServiceTest {

    @Mock
    private UserNoteRepository userNoteRepository;

    private UserNoteServiceImpl userNoteService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userNoteService = new UserNoteServiceImpl(userNoteRepository);
    }

    @Test
    public void createUserNoteTest() {
        Mockito.when(userNoteRepository.save(mockeUserNote())).thenReturn(mockeUserNote());

        userNoteService.createUserNote(mockeUserNote());
    }

    @Test
    public void findByIdTest() {

        Optional<UserNote> optionalUserNote = Optional.of(mockeUserNote());

        Mockito.when(userNoteRepository.findById(123l)).thenReturn(optionalUserNote);

        userNoteService.findById(123l);
    }

    @Test
    public void findAllByUsernameTest() {

        List<UserNote> optionalUserNote = Collections.singletonList(mockeUserNote());

        Mockito.when(userNoteRepository.findByusername("test-user")).thenReturn(optionalUserNote);

        userNoteService.findAllByUsername("test-user");
    }

    @Test
    public void updateUserNoteTest() {
        Mockito.when(userNoteRepository.save(mockeUserNote())).thenReturn(mockeUserNote());

        userNoteService.update(mockeUserNote());
    }

    @Test
    public void deleteNoteTest() {
        Mockito.doNothing().when(userNoteRepository).delete(mockeUserNote());

        userNoteService.delete(mockeUserNote());
    }

    public UserNote mockeUserNote() {
        UserNote note = new UserNote();

        note.setCategory("testCategory");
        note.setUsername("test-user");
        note.setTitle("user-title");
        note.setDescription("Test-Description");
        note.setCreatedTime(new Date());
        note.setUpdatedTime(new Date());

        return note;
    }

}
