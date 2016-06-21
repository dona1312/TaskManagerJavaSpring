package com.taskmanager.controllers;

import com.taskmanager.entity.User;
import com.taskmanager.services.auth.AuthenticationService;
import com.taskmanager.services.impl.UserServiceImpl;
import com.taskmanager.viewmodels.users.UserEditVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.*;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public ModelAndView loginUser(@ModelAttribute("model") @Valid UserEditVM model, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return new ModelAndView("form","model",model);
        }

        AuthenticationService.setLoggedUser(userService.getAll().stream().filter(u->u.getUsername().equals(model.getUsername())&&u.getPassword().equals(model.getPassword())).findFirst().orElse(null));

        if (AuthenticationService.getLoggedUser()!=null){
            return new ModelAndView("redirect:/tasks/getMyTask", "model", model);
        }else{
            bindingResult.reject("model", "Invalid username/password");

            return new ModelAndView("form");
        }
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("form","model",new UserEditVM());
    }

    @RequestMapping(value = "logout")
    public  ModelAndView logout(){
        AuthenticationService.setLoggedUser(null);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/register",method=RequestMethod.GET)
    public ModelAndView register(){
        return  new ModelAndView("register","model",new UserEditVM());
    }

    @RequestMapping(value = "/register",method=RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute("model") UserEditVM model,BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return new ModelAndView("register","model",model);
        }
        else{
            User user;
            if (model.getId()==0){
                user=new User();
            }else{
                user=userService.getByID(model.getId());
            }

            user.setUsername(model.getUsername());
            user.setPassword(model.getPassword());
            user.setIsAdmin(model.getIsAdmin());
            user.setFullName(model.getFullName());

            userService.save(user);

            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/")
    public  ModelAndView index(){
        return new ModelAndView("redirect:/login");
    }
}
