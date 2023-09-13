package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "departments")
@Data @NoArgsConstructor
public class Department {

    @Id
    @Column(name = "DEPARTMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departmentID ;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName ;

}
