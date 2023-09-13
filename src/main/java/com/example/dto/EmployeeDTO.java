package com.example.dto;

import com.example.entity.Department;
import com.example.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id ;

    @NotBlank(message = "first name is required")
    private String firstName ;

    @NotBlank(message = "last name is required")
    private String lastName ;

    @Email
    @NotBlank(message = "email is required")
    private String email ;

    @Min(value = 8000,message = "must be greater than or equal to 8000 LE")
    private double salary ;

    private String phoneNumber ;

    private LocalDate hireDate ;

    private Job job ;

    private Department department ;

}