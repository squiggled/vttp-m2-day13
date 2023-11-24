package com.example.day13demo.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.day13demo.model.Employee;
import com.example.day13demo.repo.EmployeeRepo;

import jakarta.validation.Valid;
//forms only use get and post -> get because we only want to GET the page after

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired //so you dont need to create 48923 repos for 842903 controllers. autowired ensures you use the same instance each time
    EmployeeRepo employeeRepo;

    @GetMapping("/addnew")
    public String employeeAdd(Model model){
        Employee emp = new Employee();
        model.addAttribute("employee", emp);
        return "employeeadd";
    }

    //valid - only valid data
    //model attribute - pass in whole form/employee object
    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult result, Model model) throws FileNotFoundException{

        Boolean returnResult = employeeRepo.save(employeeForm);

        // return "redirect:/employees/list"; //you  must use: redirect to get the controller to redirect you to the new location. 
        //the controller will add the new object to the list
        //you can go to employees/list again, but it wont be updated
        model.addAttribute("savedEmployee", employeeForm);
        return "success";
    }
    //get - all employees
    @GetMapping("/list")
    public String employeeList(Model model){
        List<Employee> employees = employeeRepo.findAll(); //calling function in the repository
        model.addAttribute("employees", employees);
        return "employeelist";
    }

    //we use GET here becos we are only getting the page that shows when someone is deleted
    //in the repo, u use DELETE
    @GetMapping("/employeedelete/{email}") 
    public String deleteEmployee(@PathVariable("email") String email) {

        Employee emp = employeeRepo.findByEmail(email);

        Boolean bResult = employeeRepo.delete(emp);

        return "redirect:/employees/list";
    }

    //to get the employee update form
    @GetMapping("/employeeupdate/{email}")
    public String updateEmployee(@PathVariable("email") String email, Model model) {
        Employee emp = employeeRepo.findByEmail(email);
        model.addAttribute("employee", emp);

        return "employeeupdate";  
    }

    //to send the info over to update the employee
    @PostMapping("/updEmployee")
    public String updateEmployeeRecord(@ModelAttribute("employee") Employee emp, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "employeeupdate";
        }

        employeeRepo.updateEmployee(emp);
        return "redirect:/employees/list";
    }


    
}
