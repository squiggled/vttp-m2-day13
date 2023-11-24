package com.example.day13workshop.controller;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.day13workshop.model.Contact;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @GetMapping("/addcontact")
    //model acts as a container for any data you want to pass to the view. here it's used to store the Contact object.
    public String addContact(Model model){
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "addcontact";
    }

    @PostMapping("/savecontact")
    public String saveContact(@Valid @ModelAttribute("contact") Contact contactForm, BindingResult result, Model model) throws FileNotFoundException{
        if (result.hasErrors()){
            return "addcontact"; //stay at same page if theres errors
        }  
    }
    
}
