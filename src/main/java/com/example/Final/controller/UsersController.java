package com.example.Final.controller;


import com.example.Final.model.UserModel;
import com.example.Final.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {


    private final UsersService usersService;
    public UsersController(UsersService userservice)
    {
        this.usersService=userservice;
    }


    @GetMapping("/register")
    public String getRegisterPage(Model model)
    {
        model.addAttribute("registerRequest",new UserModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model)
    {
        model.addAttribute("loginRequest",new UserModel());
        return "login_page";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel)
    {
        System.out.println("register request "+userModel);
       UserModel registerdUser =usersService.registerUser(userModel.getLogin(), userModel.getPassword(),userModel.getEmail());
            return registerdUser==null?"error_page":"redirect:/login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel)
    {
        System.out.println("login request "+userModel);
        UserModel authenticated =usersService.authenticate(userModel.getLogin(), userModel.getPassword());
        if (authenticated!=null){
            return "personal_page";
        }
        else
        {
            return "error_page";
        }

    }
}
