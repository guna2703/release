package com.user.notes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.user.notes.entity.UserAccount;
import com.user.notes.entity.UserNote;
import com.user.notes.service.LoginService;
import com.user.notes.service.UserAccountService;
import com.user.notes.service.UserNoteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Component
public class LoginController {

    @Autowired
    private LoginService service;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserNoteService userNoteService;


    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginPage(Model model){
        log.info("Login page redirection");
        model.addAttribute("loginForm", new UserAccount());
        return "login";
    }

    @PostMapping(value="login.html")
    public String showWelcomePage(Model model, @ModelAttribute("loginForm") UserAccount account) {

        boolean isValidUser = service.validateUser(account.getUsername(), account.getPassword());

        if (!isValidUser) {
            model.addAttribute("errorMessage", "Invalid Credentials");
            return "login";
        }
        model.addAttribute("username", account.getUsername());
        model.addAttribute("userNotes", userNoteService.findAllByUsername(account.getUsername()));
        model.addAttribute("usernoteForm", new UserNote());
        return "usernotes";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {

        log.info("Registration Form page redirection.");
        model.addAttribute("accountForm", new UserAccount());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute("accountForm") UserAccount userAccount) {

        log.info("object: {}", userAccount.toString());
        log.info("Register the user. name: {}", userAccount.getUsername());

        if (!(userAccount.getPassword().equals(userAccount.getConfirmPassword()))) {
            model.addAttribute("errorMessage", "Password Maching Failed");
            log.error("Password Maching Failed for the user: {}", userAccount.getUsername());
            return "registration";
        }
        String userAccountResult = userAccountService.checkUserAccount(userAccount);
        if (userAccountResult != null) {
            model.addAttribute("errorMessage", userAccountResult);
            log.error(String.format("%s for the user: %s", userAccountResult, userAccount.getUsername()));
            return "registration";
        }

        UserAccount createdAccount = userAccountService.save(userAccount);
        model.addAttribute("name", createdAccount.getUsername());

        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response){

        request.getSession().invalidate();
        return "redirect:/login?logout";
    }

}
