package com.user.notes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.notes.entity.UserNote;
import com.user.notes.repository.UserNoteRepository;

@Service
public class UserNoteServiceImpl implements UserNoteService {

    @Autowired
    private UserNoteRepository userNoteRepository;

    public UserNoteServiceImpl(UserNoteRepository userNoteRepository) {
        this.userNoteRepository = userNoteRepository;
    }

    @Override
    public UserNote createUserNote(UserNote userNote) {
        return userNoteRepository.save(userNote);
    }

    @Override
    public UserNote findById(Long id) {
        Optional<UserNote> userNote = userNoteRepository.findById(id);
        if (userNote.isPresent()) {
            return userNote.get();
        }
        return null;
    }

    @Override
    public List<UserNote> findAllByUsername(String username) {
        return userNoteRepository.findByusername(username);
    }

    @Override
    public UserNote update(UserNote userNote) {
        return userNoteRepository.save(userNote);
    }

    @Override
    public void delete(UserNote userNote) {
        userNoteRepository.delete(userNote);
    }
}
