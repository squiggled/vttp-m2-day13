package com.example.day13lesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userview") //need the function to show a user view
public class UserUIController {
    @GetMapping("/user")
    public String userForm(){
        return "createuser"; //LOCALHOST:8081/userview/user will load createuser.html
    }

    
    
}