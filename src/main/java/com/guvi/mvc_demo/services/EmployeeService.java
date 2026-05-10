package com.guvi.mvc_demo.services;

import com.guvi.mvc_demo.dto.EmployeeRequestDto;
import com.guvi.mvc_demo.mapper.EmployeeMapper;
import com.guvi.mvc_demo.model.Employee;
import com.guvi.mvc_demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Employee saveEmployee(EmployeeRequestDto employeeRequestDto) {
        var employee = EmployeeMapper.mapToEmployee(employeeRequestDto);
        return repository.save(employee);
    }

    public void saveEmployee(List<EmployeeRequestDto> employeeRequestDtos) {
        var employees = employeeRequestDtos.stream().map(EmployeeMapper::mapToEmployee).toList();
        repository.saveAllAndFlush(employees);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    public Employee getEmployeeByName(String name) {
        return repository.findByName(name);
    }

    public List<Employee> getAllEmployeeFromJdbc() {
        final String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, (rs, row) -> {
            return Employee.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .department(rs.getString("department"))
                    .build();
        });
    }

    public List<Employee> getEmployeeByDepartment(String department) {
        return repository.findByDepartment(department);
    }

    public List<Employee> getEmployeeByDepartmentWithNativeQuery(String department) {
        return repository.findByDepartmentWithNativeQuery(department);
    }
}
