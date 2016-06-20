package com.taskmanager.dao;

import com.taskmanager.entity.*;
import org.springframework.stereotype.Repository;

/**
 * Created by dona on 15.06.16.
 */
@Repository
public class UserDAO extends BaseDAO<User> {

    public UserDAO(){
        super();
        this.tClass=User.class;
    }
}
