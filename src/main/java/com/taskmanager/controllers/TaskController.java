package com.taskmanager.controllers;

import com.taskmanager.entity.Task;
import com.taskmanager.services.auth.AuthenticationService;
import com.taskmanager.services.impl.TaskServiceImpl;
import com.taskmanager.viewmodels.tasks.TaskEditVM;
import com.taskmanager.viewmodels.tasks.TaskListVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dona on 15.06.16.
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;

    @RequestMapping(value = "editTask",method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(required = false) Integer id) {

        TaskEditVM model = new TaskEditVM();
        Task task;

        if (id == null) {
            task = new Task();
        } else {
            task = taskService.getByID(id);
            if (task == null) {
                return new ModelAndView("redirect:getMyTask");
            }
        }
        model.setId(task.getId());
        model.setUser(AuthenticationService.getLoggedUser());
        model.setBody(task.getBody());
        model.setTitle(task.getTitle());

        return new ModelAndView("tasks/taskEdit", "taskObject", model);
    }

    @RequestMapping(value = "saveTask",method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute TaskEditVM vm, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getAllErrors());
            model.addAttribute("taskObject",vm);
            return  new ModelAndView("tasks/taskEdit","taskObject",vm);
        }
        else{
            Task task;
            if (vm.getId()==0){
                task=new Task();
            }else{
                task=taskService.getByID(vm.getId());
            }

            task.setTitle(vm.getTitle());
            task.setBody(vm.getBody());
            task.setUser(AuthenticationService.getLoggedUser());

            taskService.save(task);
            return new ModelAndView("redirect:getMyTask");
        }
    }

    @RequestMapping("deleteTask")
    public ModelAndView delete(@RequestParam Integer id){
        Task task=taskService.getByID(id);

        if (task==null){
            return  new ModelAndView("redirect:getMyTask");
        }
        taskService.delete(task);
        return new ModelAndView("redirect:getMyTask");
    }

    @RequestMapping(value = {"/getMyTask"})
    public ModelAndView getMyTask() {
        TaskListVM model=new TaskListVM();
        model.setTasks(taskService.getAll().stream().filter(t->t.getUser().getId()== AuthenticationService.getLoggedUser().getId()).collect(Collectors.toCollection(ArrayList<Task>::new)));

        return new ModelAndView("tasks/tasksList", "taskList", model.getTasks());
    }

    @RequestMapping(value = {"/getAll"})
    public ModelAndView getAll() {
        TaskListVM model=new TaskListVM();
        model.setTasks(taskService.getAll());

        return new ModelAndView("tasks/tasksList", "taskList", model.getTasks());
    }

    @RequestMapping(value = "/")
    public static String home() {
        return "test";
    }
}
