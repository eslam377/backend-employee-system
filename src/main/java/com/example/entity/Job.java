package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "jobs")
@Data @NoArgsConstructor
public class Job {

    @Id
    @Column(name = "JOB_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String jobId ;

    @Column(name = "JOB_TITLE")
    private String jobTitle ;

    @Column(name = "MIN_SALARY")
    private long minimumSalary ;

    @Column(name = "MAX_SALARY")
    private long maximumSalary ;

}
