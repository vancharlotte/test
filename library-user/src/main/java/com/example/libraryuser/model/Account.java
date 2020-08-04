package com.example.libraryuser.model;

import javax.persistence.*;

@Entity
@Table(name= "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;

    public Account() {
    }

    public Account(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
