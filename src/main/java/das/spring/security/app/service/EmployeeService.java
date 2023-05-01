package das.spring.security.app.service;

import das.spring.security.app.models.Employee;
import das.spring.security.app.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(final int id){
        log.info("getEmployeeById() service invoked..");
        return employeeRepository.findEmployeeById(id);
    }

    public int createEmployee(final Employee employee){
        log.info("createEmployee() service invoked..");
        return employeeRepository.insertEmployee(employee);
    }
}
