package com.example.day13demo.repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.day13demo.model.Employee;

@Repository
public class EmployeeRepo {
    
    final String dirPath = "/Users/clumped/VTTP/module-2-ssf/data"; //declare a dir
    final String fileName = "employee.txt";
    private List<Employee> employees;

    public EmployeeRepo() throws ParseException{
        if (employees==null){
            employees = new ArrayList<Employee>();
        }
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date dt = df.parse("2000-01-01");
        Employee emp1 = new Employee("bob", "bob", "bob@gmail.com", "12345689", 5000, dt, 123456);
        employees.add(emp1);

        Employee emp2 = new Employee("lee", "hsien dragon", "lhd@gmail.com", "41412", 500000, dt, 142343);
        employees.add(emp2);
    }

    //GET all employees
    public List<Employee> findAll(){
        return employees;
    }

    //delete employee
    public Boolean delete(Employee employee){
        Boolean result = false;
        int employeeIndex = employees.indexOf(employee);

        if (employeeIndex >=0){
            employees.remove(employeeIndex);
            result=true;
        }
        return result;
    }

    //GET employee by email
    public Employee findByEmail(String email){
        return employees.stream().filter(emp-> emp.getEmail().equals(email)).findFirst().get();
    }

    //POST update employee
    public Boolean updateEmployee(Employee employee){
        Boolean result = false;
        Employee empobj = employees.stream().filter(map-> map.getEmail().equals(employee.getEmail())).findFirst().get();
        int employeeIndex = employees.indexOf(empobj);

        if (employeeIndex >=0){
            //set birthday to the one passed in as the argument (which contains updated data)
            employees.get(employeeIndex).setEmail(employee.getEmail());
            employees.get(employeeIndex).setBirthday(employee.getBirthday());
            employees.get(employeeIndex).setFirstName(employee.getFirstName());
            employees.get(employeeIndex).setLastName(employee.getLastName());
            employees.get(employeeIndex).setPhoneNo(employee.getPhoneNo());
            employees.get(employeeIndex).setSalary(employee.getSalary());
            employees.get(employeeIndex).setPostalCode(employee.getPostalCode());
        }
        return result;
    }

    //POST add employee record in the listing
    public Boolean save(Employee employee) throws FileNotFoundException{
        Boolean result = false;
        result = employees.add(employee);
        File f = new File(dirPath+"/"+fileName);
        OutputStream os = new FileOutputStream(f, true);
        PrintWriter pw = new PrintWriter(os);
        pw.println(employee.toString());
        pw.flush();
        pw.close();
        return result;
    }
}
