package mybatis_spring_study.dao;

import java.sql.SQLException;

import mybatis_spring_study.dto.Employee;

public interface EmployeeDao {
	
	int insertEmployee(Employee employee);

	int deleteEmployee(Employee employee);

/*	int updateEmployee(Employee employee) throws SQLException;

	List<Employee> selectEmployeeByAll() throws SQLException;

	Employee selectEmployeeByNo(Employee employee) throws SQLException;
	
	int transactionInsertEmployeeAndDepartmentApi(Employee emp, Department dept);
	
	int transactionInsertEmployeeAndDepartment(Employee emp, Department dept);*/
}
