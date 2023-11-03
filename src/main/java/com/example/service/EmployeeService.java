package com.example.service;

import com.example.dto.EmployeeDTO;
import com.example.dto.SaveEmployeeDTO;
import com.example.entity.Employee;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeDTO> findAllEmployees(String token);

    Optional<Employee> findEmployeeById(long id);

    void deleteEmployee(@Param("id") Long id);


    EmployeeDTO saveEmployee(SaveEmployeeDTO saveEmployeeDTO);
}
