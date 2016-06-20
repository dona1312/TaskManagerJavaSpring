package com.taskmanager.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by dona on 13.06.16.
 */
@Entity
@Table(name="tasks")
public class Task extends BaseEntity{

    @NotEmpty(message = "Field required")
    @Size(min=3, max=30,message = "Title must be between 3 and 30 symbols.")
    @Column
    private String title;

    @NotEmpty(message = "Field required")
    @Size(min=3, max=250,message = "Body must be between 3 and 250 symbols.")
    @Column
    private String body;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
