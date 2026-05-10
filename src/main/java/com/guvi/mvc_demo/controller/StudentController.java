package com.guvi.mvc_demo.controller;

import com.guvi.mvc_demo.dto.EmployeeRequestDto;
import com.guvi.mvc_demo.model.Employee;
import com.guvi.mvc_demo.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class StudentController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/students")
    public String showStudents(Model model) {
        System.out.println("Controller reached! Looking for: /WEB-INF/jsp/student-list.jsp");
        List<String> students = Arrays.asList("John", "Jane", "Mike");
        model.addAttribute("list", students);
        return "student-list";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "The Controller is working!";
    }


    @GetMapping("/employee")
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/employee-jdbc")
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployeeFromJdbc() {
        return ResponseEntity.ok(employeeService.getAllEmployeeFromJdbc());
    }

    @GetMapping("/employee/{name}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployee(@PathVariable("name") String name) {
        return ResponseEntity.ok(employeeService.getEmployeeByName(name));
    }

    //Content Negotiation
    @GetMapping(value = "/employee/departments/{departmentName}")
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployeeFromDepartment(@PathVariable("departmentName") String departmentName) {
        return ResponseEntity.ok(employeeService.getEmployeeByDepartment(departmentName));
    }

    @GetMapping(value = "/employee/departments/{departmentName}", params = "nativeQuery=y")
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployeeFromDepartmentWithNativeQuery(@PathVariable("departmentName") String departmentName) {
        return ResponseEntity.ok(employeeService.getEmployeeByDepartmentWithNativeQuery(departmentName));
    }

    @PostMapping("/employee")
    @ResponseBody
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        log.info("Saving employee details %s".formatted(employeeRequestDto));
        var employee = employeeService.saveEmployee(employeeRequestDto);
        return ResponseEntity.accepted().body("The record is persisted %d".formatted(employee.getId()));
    }

    @PostMapping("/employees")
    @ResponseBody
    public ResponseEntity<String> saveEmployees(@RequestBody List<EmployeeRequestDto> employeeRequestDtos) {
        log.info("Saving employee details %s".formatted(employeeRequestDtos));
        employeeService.saveEmployee(employeeRequestDtos);
        return ResponseEntity.accepted().body("The records are persisted");
    }

    @DeleteMapping("/employee/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("The requested employee %d is deleted".formatted(id));
    }
}