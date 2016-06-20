package com.taskmanager.viewmodels;

/**
 * Created by dona on 16.06.16.
 */
import javax.validation.constraints.*;

public class UserEditVM {
    private int id;
    @NotNull @Size(min=6, max=30)
    private String username;
    @NotNull @Size(min=6, max=30)
    private String password;
    @NotNull @Size(min=6, max=30)
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
