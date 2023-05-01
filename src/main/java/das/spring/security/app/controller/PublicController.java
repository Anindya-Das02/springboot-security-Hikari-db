package das.spring.security.app.controller;

import das.spring.security.app.models.Employee;
import das.spring.security.app.models.Student;
import das.spring.security.app.models.exceptions.EmptyBodyException;
import das.spring.security.app.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/health")
    @ResponseBody
    public ResponseEntity<?> health(){
        return ResponseEntity.ok(Map.of("status",200,
                "message","public apis up & running",
                "datetime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> createRecord(final RequestEntity<Student> studentRequestEntity) throws EmptyBodyException {
        log.info("Inside create record...");
        var headers = studentRequestEntity.getHeaders();
        Student student = studentRequestEntity.getBody();
         if(student == null){
             throw new EmptyBodyException("Request body empty");
         }
         return ResponseEntity.ok(Map.of(
                 "message","record created",
                 "status",200
         ));
    }

    @GetMapping("/employee/{id}")
    @ResponseBody
    public Employee getEmployeeDetails(@PathVariable("id") int id){
        log.info("fetching employee details with empId = {}", id);
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employee/create")
    @ResponseBody
    public ResponseEntity<?> createEmployee(RequestEntity<Employee> employeeRequestEntity){
        Employee employee = employeeRequestEntity.getBody();
        int rowEffected = employeeService.createEmployee(employee);
        if(rowEffected == 0){
            return ResponseEntity.ok(Map.of("message","o rows effected/created"));
        }
        return ResponseEntity.ok(Map.of("status","inserted employee record","message","effected " + rowEffected + " rows"));
    }


}
