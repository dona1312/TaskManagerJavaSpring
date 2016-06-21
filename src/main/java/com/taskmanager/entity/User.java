package com.taskmanager.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by dona on 13.06.16.
 */
@Entity
@Table(name="users")
public class User extends BaseEntity {

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String fullName;

    @Column
    private boolean isAdmin;

    @OneToMany
    private Set<Task> tasks;

    public User() { }

    public Set<Task> getTasks() {
        return tasks;
    }
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }
}
