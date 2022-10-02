package com.microservicesmingeso.reportservice.controllers;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicesmingeso.reportservice.exceptions.NotFoundException;
import com.microservicesmingeso.reportservice.models.EmployeeModel;
import com.microservicesmingeso.reportservice.services.EmployeeService;
import com.microservicesmingeso.reportservice.services.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ReportService reportService;

    @GetMapping("/employees")
    public void exportReportEmployees(HttpServletResponse response) throws IOException {
        List<EmployeeModel> employees = employeeService.getEmployees();
        if (employees == null) {
            employees = new ArrayList<>();
            throw new NotFoundException("No existen empleados");
        }

        reportService.generateEmployeesReport(response, employees);
    }
}
