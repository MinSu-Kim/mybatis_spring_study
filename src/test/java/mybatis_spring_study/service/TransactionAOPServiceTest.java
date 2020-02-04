package mybatis_spring_study.service;

import java.sql.SQLException;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import mybatis_spring_study.AbstractTest;
import mybatis_spring_study.dto.Department;
import mybatis_spring_study.dto.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/context-root.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionAOPServiceTest extends AbstractTest {
	@Inject
	private TransactionAOPService service;

	@Test(expected = DuplicateKeyException.class)
	public void test1registerTransaction_Dept_Fail() throws SQLException {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(1, "태스크포스", 10); // DuplicateKeyException
		Employee employee = new Employee(1004, "박신혜", "과장", new Employee(4377), 4100000, department);

		int res = service.trRegister(department, employee);
		Assert.isTrue(res == 1, "Fail_Dept");
	}

	@Test(expected = DuplicateKeyException.class)
	public void test2registerTransaction_Emp_Fail() throws SQLException {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6, "태스크포스", 10);
		Employee employee = new Employee(4377, "박신혜", "과장", new Employee(4377), 4100000, department); // DuplicateKeyException

		int res = service.trRegister(department, employee);
		Assert.isTrue(res == 1, "Emp_Fail");
	}

	@Test
	public void test3registerTransaction_Success() throws SQLException {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6, "태스크포스", 10);
		Employee employee = new Employee(1005, "박신혜", "과장", new Employee(4377), 4100000, department);

		int res = service.trRegister(department, employee);
		Assert.isTrue(res == 2, "Transaction_Success");
	}

	@Test(expected = RuntimeException.class)
	public void test4UnregisterTransaction_Fail_Dept() throws SQLException {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(100); // RuntimeException -> rollback
		Employee employee = new Employee(1005); // rollback 되므로 삭제되면 안됨

		int res = service.trUnRegister(department, employee);
		Assert.isTrue(res == 1, "Fail_Dept");
	}

	@Test(expected = RuntimeException.class)
	public void test5UnregisterTransaction_Fail_Emp() throws SQLException {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6);// 정상삭제
		Employee employee = new Employee(9999);// RuntimeException -> rollback

		int res = service.trUnRegister(department, employee);
		Assert.isTrue(res == 1, "Fail_Emp");
	}

	@Test
	public void test6UnregisterTransaction_Success() throws SQLException {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6);
		Employee employee = new Employee(1005);

		int res = service.trUnRegister(department, employee);
		Assert.isTrue(res == 2, "Transaction_Success");
	}
}
