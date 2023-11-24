package com.example.day13demo.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @NotEmpty(message="First name is mandatory")
    @Size (min=2, max=20, message="First name must be more than 2 characters and less than 20")
    private String firstName;

    @NotEmpty(message="Last name is mandatory")
    @Size (min=2, max=20, message="Last name must be more than 2 characters and less than 20")
    private String lastName;
   
    @Email(message="Please enter a valid email format")
    @NotBlank(message="Please enter an email")
    private String email;

    @Pattern(regexp = "(8|9)[0-9]{7}",message="Invalid phone number")
    private String phoneNo;

    @Min(value=1500, message="Invalid")
    @Max(value=500000, message="u not LHL")
    private Integer salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message="Birth date must be a past date")
    private Date birthday;

    @Digits(fraction=0, integer=6, message="Please enter 6 digits")
    @Min(value=111111, message="starts from 111111")
    private Integer postalCode;
}
