package com.taskmanager.viewmodels.users;

/**
 * Created by dona on 16.06.16.
 */
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;

public class UserEditVM {

    private int id;

    @NotEmpty(message = "Field usernamerequired")
    @Size(min=3, max=30,message = "Username must be between 3 and 30 symbols.")
    private String username;

    @NotEmpty(message = "Field password required")
    @Size(min=3, max=30,message = "Password must be between 3 and 30 symbols.")
    private String password;

//    @NotEmpty(message = "Field full name required")
    private String fullName;

    @NotNull
    private boolean isAdmin;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
