package com.example.springboot1.Controller;

import com.example.springboot1.repository.CompanyRepository;
import com.example.springboot1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/employee")
public class CompanyController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;


}
