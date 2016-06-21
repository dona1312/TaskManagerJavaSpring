package com.taskmanager.controllers;

import com.taskmanager.entity.User;
import com.taskmanager.services.auth.AuthenticationService;
import com.taskmanager.services.impl.UserServiceImpl;
import com.taskmanager.viewmodels.users.UserEditVM;
import com.taskmanager.viewmodels.users.UserListVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "editUser",method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(required = false) Integer id){

        UserEditVM model = new UserEditVM();
        User user;

        if (id == null) {
            user=new User();
        } else {
           user=userService.getByID(id);
            if (user == null) {
                return new ModelAndView("redirect:getMyTask");
            }
        }

        model.setId(user.getId());
        model.setFullName(user.getFullName());
        model.setPassword(user.getPassword());
        model.setUsername(user.getUsername());
        model.setIsAdmin(user.getIsAdmin());

        return  new ModelAndView("users/userEdit","model",model);
    }

    @RequestMapping(value = "saveUser",method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("model") UserEditVM model, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return  new ModelAndView("/users/userEdit","model",model);
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
            return new ModelAndView("redirect:getAll");
        }
    }

    @RequestMapping("deleteUser")
    public ModelAndView delete(@RequestParam Integer id){
        User user=userService.getByID(id);
        if (user==null){
            return new ModelAndView("redirect:getAll");
        }else{
            userService.delete(user);
            return new ModelAndView("redirect:getAll");
        }
    }

    @RequestMapping(value = {"/getAll"})
    public ModelAndView getAll() {

        List<User> userList = userService.getAll();
        UserListVM model=new UserListVM();
        model.setUsers(userList);

        return new ModelAndView("users/usersList", "usersList", model.getUsers());

    }

    @RequestMapping(value = "/")
    public static String home() {
    return "test";
}
}
