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
    public ModelAndView loginUser(@ModelAttribute @Valid UserEditVM user, BindingResult bindingResult, ModelMap model){

        if (bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getAllErrors());
            model.addAttribute("user",user);
            return new ModelAndView("form","user",user);
        }

        AuthenticationService.setLoggedUser(userService.getAll().stream().filter(u->u.getUsername().equals(user.getUsername())&&u.getPassword().equals(user.getPassword())).findFirst().orElse(null));
        if (AuthenticationService.getLoggedUser()!=null){
            return new ModelAndView("redirect:/tasks/getMyTask");
        }else{
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("form","user",new User());
    }

    @RequestMapping(value = "logout")
    public  ModelAndView logout(@ModelAttribute UserEditVM user){
        AuthenticationService.setLoggedUser(null);
        return  new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/register",method=RequestMethod.GET)
    public ModelAndView register(){
        return  new ModelAndView("register","user",new User());
    }

    @RequestMapping(value = "/register",method=RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute UserEditVM vm,BindingResult bindingResult,ModelMap model){

        if (bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getAllErrors());
            model.addAttribute("user",vm);
            return new ModelAndView("register","user",vm);
        }
        else{
                User user;
                if (vm.getId()==0){
                    user=new User();
                }else{
                    user=userService.getByID(vm.getId());
                }

                user.setUsername(vm.getUsername());
                user.setPassword(vm.getPassword());
                user.setIsAdmin(vm.getIsAdmin());
                user.setFullName(vm.getFullName());

                userService.save(user);
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/")
    public  ModelAndView index(){
        return new ModelAndView("redirect:/login");
    }
}
