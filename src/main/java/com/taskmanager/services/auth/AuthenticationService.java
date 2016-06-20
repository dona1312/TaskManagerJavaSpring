package com.taskmanager.services.auth;

import com.taskmanager.dao.UserDAO;
import com.taskmanager.entity.User;
import com.taskmanager.services.impl.UserServiceImpl;
import com.taskmanager.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by dona on 15.06.16.
 */
public class AuthenticationService {

    public static User loggedUser;

    public  static User getLoggedUser(){
        return loggedUser;
    }
    public static void setLoggedUser(User user){
        loggedUser=user;
    }
}
