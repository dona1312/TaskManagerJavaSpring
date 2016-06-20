package com.taskmanager.dao;

import com.taskmanager.entity.Task;
import com.taskmanager.services.auth.AuthenticationService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by dona on 15.06.16.
 */
@Repository
public class TaskDAO extends BaseDAO<Task> {

    public TaskDAO(){
        super();
        this.tClass=Task.class;
    }
}
