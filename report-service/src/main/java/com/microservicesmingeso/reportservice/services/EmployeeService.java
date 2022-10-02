package com.microservicesmingeso.reportservice.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicesmingeso.reportservice.models.EmployeeModel;

@Service
public class EmployeeService {

    @Autowired
    RestTemplate restTemplate;

    public List<EmployeeModel> getEmployees() {
        String url = "http://employee-service/employees/all";
        ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class); // Se usa lista de Object para mapear la repuesta JSON
        Object[] records = response.getBody(); // Obtener lista de empleados desde servicio empleados
        if (records == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper(); // Mapper desde object a modelo Empleado
        return Arrays.stream(records)
                .map(employee -> mapper.convertValue(employee, EmployeeModel.class))
                .collect(Collectors.toList());
    }
}
