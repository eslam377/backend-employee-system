package com.example.controllers;

import com.example.constants.AppConstant;
import com.example.dto.EmployeeDTO;
import com.example.dto.SaveEmployeeDTO;
import com.example.entity.Employee;
import com.example.exceptions.RecordNotFoundException;
import com.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private final EmployeeService employeeService ;


    @GetMapping("/employees")
    public ResponseEntity<?> findAllEmployee(){
        try {
            List<EmployeeDTO> employeeDTOs = employeeService.findAllEmployees();
            return new ResponseEntity<>(employeeDTOs ,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable("id") long id){
        Employee employee = employeeService.findEmployeeById(id).orElseThrow(()-> new RecordNotFoundException(AppConstant.USER_NOT_FOUND+id));
        return new ResponseEntity<>(employee,HttpStatus.OK) ;
    }

    @PostMapping("/employees")
    public ResponseEntity<?> saveEmployee(@RequestBody SaveEmployeeDTO saveEmployeeDTO){
        try {
            return new ResponseEntity<>(employeeService.saveEmployee(saveEmployeeDTO),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
