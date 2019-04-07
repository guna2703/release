package com.user.notes.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.user.notes.entity.UserAccount;
import com.user.notes.entity.UserNote;
import com.user.notes.service.UserAccountService;
import com.user.notes.service.UserNoteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Component
public class UserNoteController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserNoteService userNoteService;

    @RequestMapping(value = "/usernotes", method = RequestMethod.POST)
    public String createNote(ModelMap model, @ModelAttribute("userNoteForm") UserNote userNote) {

        log.info("Create new user note: {}", userNote.toString());
        UserAccount account = userAccountService.findByUsername(userNote.getUsername());

        userNote.setUserAccount(account);
        userNote.setCreatedTime(new Date());
        userNote.setUpdatedTime(new Date());

        userNoteService.createUserNote(userNote);
        model.put("username", userNote.getUsername());
        model.put("userNotes", userNoteService.findAllByUsername(userNote.getUsername()));
        model.addAttribute("usernoteForm", new UserNote());
        return "usernotes";
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String doActionUserNote(ModelMap model, @PathVariable("id") String userNoteId) {

        UserNote userNote = userNoteService.findById(Long.valueOf(userNoteId));

        model.put("showItem", true);
        model.put("username", userNote.getUsername());
        model.put("userNote", userNote);
        return "usernoteAction";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editNote(ModelMap model, @PathVariable("id") String userNoteId) {

        UserNote userNote = userNoteService.findById(Long.valueOf(userNoteId));

        model.put("updateItem", true);
        model.put("username", userNote.getUsername());
        model.put("userNote", userNote);
        return "usernoteAction";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateNote(ModelMap model, @PathVariable("id") String userNoteId,
            @ModelAttribute("userNote") UserNote userNote) {

        UserNote savedUserNoteUserNote = userNoteService.findById(Long.valueOf(userNoteId));

        savedUserNoteUserNote.setCategory(userNote.getCategory());
        savedUserNoteUserNote.setTitle(userNote.getTitle());
        savedUserNoteUserNote.setDescription(userNote.getDescription());
        savedUserNoteUserNote.setUpdatedTime(new Date());

        userNoteService.update(savedUserNoteUserNote);

        model.put("username", savedUserNoteUserNote.getUsername());
        model.put("userNotes", userNoteService.findAllByUsername(savedUserNoteUserNote.getUsername()));
        model.addAttribute("usernoteForm", new UserNote());
        return "usernotes";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteNote(ModelMap model, @PathVariable("id") String userNoteId) {

        UserNote userNote = userNoteService.findById(Long.valueOf(userNoteId));

        userNoteService.delete(userNote);

        model.put("username", userNote.getUsername());
        model.put("userNotes", userNoteService.findAllByUsername(userNote.getUsername()));
        model.addAttribute("usernoteForm", new UserNote());
        return "usernotes";
    }
}
