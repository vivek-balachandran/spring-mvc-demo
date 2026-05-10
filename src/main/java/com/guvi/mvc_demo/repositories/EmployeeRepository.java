package com.guvi.mvc_demo.repositories;

import com.guvi.mvc_demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
// Spring Data JPA generates CRUD methods automatically

    //SELECT * FROM employee emp WHERE emp.name = 'Vivek';
    //JPA Method Query
    Employee findByName(String name);

    //JPA Named Query from JQPL
    @Query(value = "SELECT e FROM Employee e WHERE e.department = :department")
    List<Employee> findByDepartment(String department);

    //JPA Named Query from JQPL
    @Query(value = "SELECT * FROM Employee e WHERE e.department = :department", nativeQuery = true)
    List<Employee> findByDepartmentWithNativeQuery(String department);
}
