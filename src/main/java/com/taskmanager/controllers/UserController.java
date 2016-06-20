package com.taskmanager.controllers;

import com.taskmanager.entity.User;
import com.taskmanager.services.auth.AuthenticationService;
import com.taskmanager.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.WebFilter;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by dona on 13.06.16.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("createUser")
    public ModelAndView create(@ModelAttribute User user){

        ModelMap model = new ModelMap();
        model.addAttribute("id",user.getId());
        model.addAttribute("name",user.getFullName());
        model.addAttribute("username",user.getUsername());
        model.addAttribute("password",user.getPassword());
        model.addAttribute("isAdmin",user.getIsAdmin());

        return new ModelAndView("users/userEdit","model",user);
    }

    @RequestMapping("editUser")
    public ModelAndView edit(@RequestParam int id,@ModelAttribute User user){
        user=userService.getByID(id);
        if (user==null){
            return  new ModelAndView("redirect:getAll");
        }else{
            ModelMap model = new ModelMap();
            model.addAttribute("id",user.getId());
            model.addAttribute("name",user.getFullName());
            model.addAttribute("username",user.getUsername());
            model.addAttribute("password",user.getPassword());
            model.addAttribute("isAdmin",user.getIsAdmin());
            return  new ModelAndView("users/userEdit","userObject",user);
        }

    }

    @RequestMapping("saveUser")
    public ModelAndView save(@Valid @ModelAttribute User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ModelAndView("redirect:getAll");
        }

        if (user.getId()!=0){
            userService.update(user);
        }
        else{
            userService.create(user);
        }
        return  new ModelAndView("redirect:getAll");
    }

    @RequestMapping("deleteUser")
    public ModelAndView delete(@RequestParam Integer id){
        User user=userService.getByID(id);
        userService.delete(user);
        return  new ModelAndView("redirect:getAll");
    }

    @RequestMapping(value = {"/getAll"})
    public ModelAndView getAll() {
        if (AuthenticationService.getLoggedUser()!=null){
            List<User> userList = userService.getAll();
            return new ModelAndView("users/usersList", "usersList", userList);
        }
        else{
            return  new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/")
    public static String home() {
    return "test";
}
}
