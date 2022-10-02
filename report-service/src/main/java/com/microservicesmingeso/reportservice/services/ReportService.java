package com.microservicesmingeso.reportservice.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.microservicesmingeso.reportservice.models.EmployeeModel;

@Service
public class ReportService {

    public void generateEmployeesReport(HttpServletResponse response, List<EmployeeModel> employees) throws IOException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String contentHeaderKey = "Content-Disposition";
        String filename = String.format("employees-%s.xlsx", currentDateTime);
        String contentHeaderValue = String.format("attachment; filename=%s", filename);
        response.setHeader(contentHeaderKey, contentHeaderValue);

        EmployeeExportService employeesReport = new EmployeeExportService(employees);
        employeesReport.generateFile(response);
    }
}
