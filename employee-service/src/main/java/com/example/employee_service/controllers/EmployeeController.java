package com.example.employee_service.controllers;

import com.example.employee_service.entites.Employee;
import com.example.employee_service.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        LOGGER.info("Adding Employee");
        Employee addedEmployee = employeeRepository.addEmployee(employee);
        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        LOGGER.info("Get all department");
        List<Employee> departments = employeeRepository.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        LOGGER.info("Employee get by id");
        Employee department = employeeRepository.findById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Employee>> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return new ResponseEntity<>(employeeRepository.findByDepartment(departmentId), HttpStatus.OK);
    }

}
