package com.microservicesmingeso.reportservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeModel {
    private Long id;
    private String identification;
    private String first_name;
    private String last_name;
    private String entrance_date;
    private String departure_date;
    private Integer base_salary;
}
