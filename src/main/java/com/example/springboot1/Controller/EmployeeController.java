package com.example.springboot1.Controller;

import com.example.springboot1.model.Company;
import com.example.springboot1.repository.CompanyRepository;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.example.springboot1.model.Employee;
import com.example.springboot1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompanyRepository companyRepository;
    @GetMapping("/")
//    @RequestMapping(value = "/list")
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
    // Create: Show form to add a new employee
    @GetMapping("/new")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee()); // Create a new Employee object for the form
        return "addEmployee"; // Return the view name for adding an employee
    }

    // Create: Save a new employee
    @PostMapping("/")
    public String addEmployee(Model model) {
        Employee employee = new Employee();
//        employeeRepository.save(employee); // Save the new employee to the database
        List<Company> companies =  companyRepository.findAll();
        model.addAttribute("companies", companies); // Create a new
        return "redirect:/";
    }

    // Update: Show form to edit an existing employee
    @GetMapping("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return "error"; // Redirect or return an error view if not found
        }
        model.addAttribute("employee", employee);
        return "editEmployee"; // Return the view name for editing an employee
    }

    // Update: Save changes to an existing employee
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute Employee updatedEmployee) {
        updatedEmployee.setId(id); // Set the ID of the updated employee
        employeeRepository.save(updatedEmployee); // Save changes to the database
        return "redirect:/"; // Redirect to the list of employees after updating
    }

    @RequestMapping(value = "/save")
    public String saveUpdate(
            @RequestParam(value = "id", required = false) Long id,
            @Valid Employee employee, BindingResult result)
    {
        if (result.hasErrors()) {
            if (id == null) {
                return "employeeAdd";
            } else {
                return "employeeUpdate";
            }
        }
        employee.setId(id);
        employeeRepository.save(employee);
        return "redirect:/t";
    }

    // Delete: Delete an existing employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id); // Delete the employee from the database
        return "redirect:/"; // Redirect to the list of employees after deleting
    }
}
