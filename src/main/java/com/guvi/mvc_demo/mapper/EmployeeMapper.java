package com.guvi.mvc_demo.mapper;

import com.guvi.mvc_demo.dto.EmployeeRequestDto;
import com.guvi.mvc_demo.model.Employee;

public interface EmployeeMapper {
    static Employee mapToEmployee(EmployeeRequestDto employeeRequestDto) {
        return Employee.builder()
                .department(employeeRequestDto.getDepartment())
                .name(employeeRequestDto.getName())
                .build();
    }
}
