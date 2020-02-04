package mybatis_spring_study.daoimpl;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import mybatis_spring_study.dao.EmployeeDao;
import mybatis_spring_study.dto.Employee;

@Repository /* @Repository - DAO객체를 Spring에게 인식시키기 위한 의미*/
public class EmployeeDaoImpl implements EmployeeDao {
	private String namespace = "mybatis_spring_study.dao.EmployeeDao";
	
	@Inject /* Spring이 생성해서  주입 */
	private SqlSession sqlSession;
	
	@Override
	public int insertEmployee(Employee employee) {
		return sqlSession.insert(namespace+".insertEmployee", employee);
	}

	@Override
	public int deleteEmployee(Employee employee) {
		return sqlSession.delete(namespace+".deleteEmployee", employee);
	}

}
