package com.example.department_service.controllers;

import com.example.department_service.client.EmployeeClient;
import com.example.department_service.entites.Department;
import com.example.department_service.repositories.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final EmployeeClient employeeClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController(DepartmentRepository departmentRepository, EmployeeClient employeeClient) {
        this.departmentRepository = departmentRepository;
        this.employeeClient = employeeClient;
    }

    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        LOGGER.info("Adding Department");
        Department addedDepartment = departmentRepository.addDepartment(department);
        return new ResponseEntity<>(addedDepartment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        LOGGER.info("Get all department");
        List<Department> departments = departmentRepository.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id) {
        LOGGER.info("Department get by id");
        Department department = departmentRepository.findById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        LOGGER.info("Department find");
        List<Department> departments
                = departmentRepository.findAll();
        departments.forEach(department ->
                department.setEmployees(
                        employeeClient.findByDepartment(department.getId())));
        return  departments;
    }
}
