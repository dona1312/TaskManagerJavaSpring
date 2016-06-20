package com.taskmanager.services.impl;

import com.taskmanager.dao.BaseDAO;
import com.taskmanager.dao.UserDAO;
import com.taskmanager.entity.User;
import com.taskmanager.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dona on 13.06.16.
 */
@Service
public class UserServiceImpl implements BaseService<User> {

//    @Qualifier("userDAO")
    @Autowired()
    private UserDAO userDAO;

    @Override
    public void create(User user) {
        userDAO.create(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }
    @Override
    public User getByID(int id) {
        return userDAO.getByID(id);
    }
}
