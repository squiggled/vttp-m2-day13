package com.example.day13lesson.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Employee {

    @NotNull(message="Please enter a name")
    @Size(min=2, message="Name must be at least 2 characters long")
    private String name;

    @NotNull
    @Email(message="Please enter a valid email")
    private String email;
    private String telephone;


}
