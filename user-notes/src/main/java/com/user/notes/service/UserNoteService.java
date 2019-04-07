package com.user.notes.service;

import java.util.List;

import com.user.notes.entity.UserNote;


public interface UserNoteService {

    UserNote createUserNote(UserNote userNote);

    UserNote findById(Long id);

    List<UserNote> findAllByUsername(String username);

    void delete(UserNote userNote);

    UserNote update(UserNote userNote);

}
