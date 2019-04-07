package com.user.notes.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_notes")
public class UserNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn
    private UserAccount userAccount;

    private String username;

    private String category;

    private String description;

    private Date createdTime;

    private Date updatedTime;
}
