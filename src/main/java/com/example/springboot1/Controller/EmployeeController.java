package com.example.springboot1.Controller;

import org.springframework.ui.Model;
import com.example.springboot1.model.Employee;
import com.example.springboot1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping("/")
    public String getAllEmployee(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        System.out.println("employees");
        model.addAttribute("employees", employees); // Use addAttribute instead of addText
        return "employeeList";
    }
    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeRepository.getById(id);
        model.addAttribute("employee", employee);
        return "employeeDetail";
    }
}
