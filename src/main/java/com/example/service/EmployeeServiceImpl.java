package com.example.service;

import com.example.dto.EmployeeDTO;
import com.example.dto.SaveEmployeeDTO;
import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.entity.Job;
import com.example.exceptions.RecordNotFoundException;
import com.example.mapper.CustomModelMapper;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;
import com.example.repository.JobRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository ;

    private final JobRepository jobRepository ;

    private final DepartmentRepository departmentRepository	;

    @Value("${bezkoder.app.jwtSecret}${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    @Override
    public List<EmployeeDTO> findAllEmployees(String token) {

        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = CustomModelMapper.mapAll(employees,EmployeeDTO.class);

        String jwtToken = token.substring(7);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtToken)))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

        String firstName = claims.get("firstName", String.class);
        String lastName = claims.get("lastName", String.class);

        log.info("User FirstName: " + firstName + ", LastName: " + lastName);

        return employeeDTOs;
    }

    @Override
    public Optional<Employee> findEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee ;
    }


    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDTO saveEmployee(SaveEmployeeDTO saveEmployeeDTO) {

        Job job = jobRepository.findByJobId(saveEmployeeDTO.getJobId());

        Department department =
                departmentRepository.findByDepartmentID(saveEmployeeDTO.getDepartmentId());

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setJob(job);
        employeeDTO.setDepartment(department);
        employeeDTO.setEmail(saveEmployeeDTO.getEmail());
        employeeDTO.setSalary(saveEmployeeDTO.getSalary());
        employeeDTO.setHireDate(saveEmployeeDTO.getHireDate());
        employeeDTO.setFirstName(saveEmployeeDTO.getFirstName());
        employeeDTO.setLastName(saveEmployeeDTO.getLastName());
        employeeDTO.setPhoneNumber(saveEmployeeDTO.getPhoneNumber());



        System.out.println(employeeDTO.toString());
        Employee employee = CustomModelMapper.map(employeeDTO,Employee.class);
        Employee empl = employeeRepository.save(employee);
        EmployeeDTO emplDTO = CustomModelMapper.map(empl,EmployeeDTO.class);

        return emplDTO;
    }


}
