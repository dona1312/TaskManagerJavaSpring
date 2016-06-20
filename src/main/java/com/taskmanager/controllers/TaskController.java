package com.taskmanager.controllers;

import com.taskmanager.entity.Task;
import com.taskmanager.services.auth.AuthenticationService;
import com.taskmanager.services.impl.TaskServiceImpl;
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

    @RequestMapping("createTask")
    public ModelAndView create(@ModelAttribute Task task){

        ModelMap model = new ModelMap();
        model.addAttribute("id",task.getId());
        model.addAttribute("title",task.getTitle());
        model.addAttribute("body",task.getBody());
        model.addAttribute("user",AuthenticationService.loggedUser.getId());

        return new ModelAndView("tasks/taskEdit","model",task);
    }

    @RequestMapping("editTask")
    public ModelAndView edit(@RequestParam int id, @ModelAttribute Task task){
        task=taskService.getByID(id);

        if (task==null){
            return  new ModelAndView("redirect:getMyTask");
        }else {
            ModelMap model = new ModelMap();
            model.addAttribute("id", task.getId());
            model.addAttribute("title", task.getTitle());
            model.addAttribute("body", task.getBody());
            if (AuthenticationService.getLoggedUser()==null){
                return new ModelAndView("tasks/taskEdit", "taskObject", task);
            }
            model.addAttribute("user", AuthenticationService.loggedUser.getId());
            return new ModelAndView("tasks/taskEdit", "taskObject", task);
        }
    }

    @RequestMapping(value = "saveTask",method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute Task task, BindingResult bindingResult, ModelMap model){
        task.setUser(AuthenticationService.loggedUser);

        if (bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getAllErrors());
            model.addAttribute("task",task);
            return  new ModelAndView("tasks/taskEdit","task",task);
        }
        else{
            if (task.getId()!=0){

                taskService.update(task);
            }
            else{
                taskService.create(task);
            }
            return  new ModelAndView("redirect:getMyTask");
        }

    }

    @RequestMapping("deleteTask")
    public ModelAndView delete(@RequestParam Integer id){
        Task task=taskService.getByID(id);
        taskService.delete(task);
        return new ModelAndView("redirect:getMyTask");
    }

    @RequestMapping(value = {"/getMyTask"})
    public ModelAndView getMyTask() {
        List<Task> taskList = taskService.getAll().stream().filter(t->t.getUser().getId()== AuthenticationService.loggedUser.getId()).collect(Collectors.toCollection(ArrayList<Task>::new));

        return new ModelAndView("tasks/tasksList", "taskList", taskList);
    }
    @RequestMapping(value = {"/getAll"})
    public ModelAndView getAll() {
        List<Task> taskList = taskService.getAll();

        return new ModelAndView("tasks/tasksList", "taskList", taskList);
    }

    @RequestMapping(value = "/")
    public static String home() {
        return "test";
    }
}
