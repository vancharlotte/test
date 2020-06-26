package com.example.authentication.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.account_id_seq")
    private int id;

    private String username;

    private String password;

    private String roles;

  //  private String permissions;

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

  /*  public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }*/


    // Modifie String role en list role
    public List<String> getRolesList() {
            if (this.roles.length() > 0) {
                return Arrays.asList(this.roles.split(","));
            }
            return new ArrayList<>();
        }

    /*    public List<String> getPermissionsList() {
            if (this.permissions.length() > 0) {
                return Arrays.asList(this.permissions.split(","));
            }
            return new ArrayList<>();
        }
    }*/
}
