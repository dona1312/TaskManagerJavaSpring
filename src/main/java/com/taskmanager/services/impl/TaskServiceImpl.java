package com.taskmanager.services.impl;

import com.taskmanager.dao.*;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.User;
import com.taskmanager.services.BaseService;
import com.taskmanager.services.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dona on 15.06.16.
 */
@Service
public class TaskServiceImpl implements BaseService<Task> {
    @Autowired
    private TaskDAO taskDAO;

    @Override
    public void create(Task task) {
        taskDAO.create(task);
    }

    @Override
    public void update(Task task) {
        taskDAO.update(task);
    }

    @Override
    public void delete(Task task) {
        taskDAO.delete(task);
    }

    @Override
    public List<Task> getAll() {
        return taskDAO.getAll();
    }
    @Override
    public Task getByID(int id) {
        return taskDAO.getByID(id);
    }

    @Override
    public void save(Task item) {
        if (item.getId()!=0){
            taskDAO.update(item);
        }
        else{
            taskDAO.create(item);
        }
    }
}
