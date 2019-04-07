package com.user.notes.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String username;

    private String password;

    @Transient
    private String confirmPassword;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
    private Set<UserNote> userNotes;

    private Date dateOfBirth;

    private String emailId;

    private String mobileNumber;
}
