package com.taskmanager.viewmodels.tasks;

import com.taskmanager.entity.Task;
import com.taskmanager.entity.User;

import java.util.List;

/**
 * Created by dona on 20.06.16.
 */
public class TaskListVM {
    List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
