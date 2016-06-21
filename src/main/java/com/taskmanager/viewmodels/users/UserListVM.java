package com.taskmanager.viewmodels.users;

import com.taskmanager.entity.User;

import java.util.List;

/**
 * Created by dona on 16.06.16.
 */
public class UserListVM {

    public List<User> users;
    public List<User> getUsers(){
        return this.users;
    }
    public  void setUsers(List<User> users){
        this.users=users;
    }
}
