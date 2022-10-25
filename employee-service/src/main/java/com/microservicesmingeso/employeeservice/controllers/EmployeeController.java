package com.microservicesmingeso.employeeservice.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.microservicesmingeso.employeeservice.entities.EmployeeEntity;
import com.microservicesmingeso.employeeservice.services.EmployeeService;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity employee) {
        try {
            employeeService.createEmployee(employee);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            System.out.println("Error:"+ e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        try {
            List<EmployeeEntity> employees = employeeService.getAllEmployees();
            if (employees.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            System.out.println("Error:"+ e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id) {
        try {
            EmployeeEntity employee = employeeService.getEmployeeById(id);
            if (employee == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            System.out.println("Error:"+ e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<EmployeeEntity> getEmployeeByIdentification(@RequestParam(required = false) String identification) {
        try {
            EmployeeEntity employee = employeeService.getEmployeeByIdentification(identification);
            if (employee == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            System.out.println("Error:"+ e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployeeById(@PathVariable("id") Long id, @RequestBody EmployeeEntity employee_updated) {
        try {
            EmployeeEntity employee = employeeService.updateEmployeeById(id, employee_updated);
            if (employee == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            System.out.println("Error:"+ e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeEntity> deleteEmployeeById(@PathVariable("id") Long id) {
        try {
            Boolean is_deleted = employeeService.deleteEmployeeById(id);
            if (!is_deleted) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println("Error:"+ e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
