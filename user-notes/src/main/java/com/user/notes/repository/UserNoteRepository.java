package com.user.notes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.notes.entity.UserNote;


public interface UserNoteRepository extends JpaRepository<UserNote, Long> {

    List<UserNote> findByusername(String username);
}
