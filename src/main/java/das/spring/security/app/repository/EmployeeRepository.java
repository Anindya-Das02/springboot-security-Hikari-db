package das.spring.security.app.repository;

import das.spring.security.app.mapper.EmployeeMapper;
import das.spring.security.app.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Employee findEmployeeById(final int id){
        String findEmployeeByIdSql = "select * from employees where id = ?";
        Employee employee = jdbcTemplate.queryForObject(findEmployeeByIdSql, new EmployeeMapper(), id);
        return employee;
    }

    public int insertEmployee(final Employee employee){
        String insertEmployeeQuery = "insert into employees (id,name,yoe,tech) values (?,?,?,?)";
        Object[] params = new Object[]{
                employee.getId(),
                employee.getName(),
                employee.getYoe(),
                employee.getTech()
        };
        logQuery(insertEmployeeQuery,params);
        return jdbcTemplate.update(insertEmployeeQuery, params);
    }

    private void logQuery(String query, Object... params){
        log.info("EmployeeRepository.insertEmployee => Statement: {}",query);
        log.info("EmployeeRepository.insertEmployee => parameters: {}",params);
    }
}
