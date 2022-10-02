package com.microservicesmingeso.employeeservice.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservicesmingeso.employeeservice.entities.EmployeeEntity;
import com.microservicesmingeso.employeeservice.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    public EmployeeEntity getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public EmployeeEntity getEmployeeByIdentification(String identification) {
        return  employeeRepository.findByIdentification(identification).orElse(null);
    }

    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity updateEmployeeById(Long id, EmployeeEntity employee_updated) {
        EmployeeEntity employee = getEmployeeById(id);
        if (employee == null) {
            return null;
        }
        employee.setFirst_name(employee_updated.getFirst_name());
        employee.setLast_name(employee_updated.getLast_name());
        employee.setEntrance_date(employee_updated.getEntrance_date());
        employee.setDeparture_date(employee_updated.getDeparture_date());
        employee.setBase_salary(employee_updated.getBase_salary());
        employeeRepository.save(employee);
        return employee;
    }

    public Boolean deleteEmployeeById(Long id) {
        EmployeeEntity employee = getEmployeeById(id);
        if (employee == null) {
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }
}
