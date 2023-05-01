package das.spring.security.app.mapper;

import das.spring.security.app.models.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee>{

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setYoe(rs.getInt("yoe"));
        employee.setTech(rs.getString("tech"));
        return employee;
    }
}
