package mybatis_spring_study.daoimpl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import mybatis_spring_study.dao.DepartmentDao;
import mybatis_spring_study.dto.Department;

@Repository /* @Repository - DAO객체를 Spring에게 인식시키기 위한 의미 */
public class DepartmentDaoImpl implements DepartmentDao {

	private String namespace = "mybatis_spring_study.dao.DepartmentDao";

	@Inject /* Spring이 생성해서 주입 */
	private SqlSession sqlSession;

	@Override
	public int deleteDepartment(Department department) {
		return sqlSession.delete(namespace + ".deleteDepartment", department);
	}

	@Override
	public int insertDepartment(Department department) {
		return sqlSession.insert(namespace + ".insertDepartment", department);
	}

	@Override
	public int updateDepartment(Department department) {
		return sqlSession.update(namespace + ".updateDepartment", department);
	}

	@Override
	public Department selectDepartmentByNo(Department department) {
		return sqlSession.selectOne(namespace + ".selectDepartmentByNo", department);
	}

	@Override
	public List<Department> selectDepartmentByAll() {
		return sqlSession.selectList(namespace + ".selectDepartmentByAll");
	}

}
