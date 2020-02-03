package mybatis_spring_study.jdbc;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import mybatis_spring_study.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/context-root.xml"})
public class DataSourceTest extends AbstractTest{

	@Inject
	private DataSource dataSource;


	
	@Test
	public void testDataSource() throws SQLException {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		log.debug("DataSource " + dataSource);
		log.debug("LoginTimeout " + dataSource.getLoginTimeout());
		Assert.notNull(dataSource, "The class must not be null");
	}
	
	@Inject
    private SqlSessionFactory sessionFactory;
	
	@Test
	public void testOpenSession() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		SqlSession session = sessionFactory.openSession();
		log.debug("session " + session);
		Assert.notNull(session, "The class must not be null");
	}
	
	/*	
	

	@Test
	public void testConnection() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		try (Connection con = dataSource.getConnection()) {
			log.debug("Connection " + con);
			Assert.assertNotNull(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetDataSource() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Connection[] connections = new Connection[10];

		try {
			for (int i = 0; i < 10; i++) {
				connections[i] = dataSource.getConnection();
				Assert.assertNotNull(connections[i]);
				printDriverStats();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testOpenSession() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		SqlSession session = sessionFactory.openSession();
		log.debug("session " + session);
		Assert.assertNotNull(session);
	}
	
	public void printDriverStats() throws SQLException {
		HikariDataSource hds = (HikariDataSource) dataSource ;
		log.trace(String.format("getMinimumIdle: %s", hds.getMinimumIdle()));
		log.trace(String.format("getMaximumPoolSize: %d", hds.getMaximumPoolSize()));
		log.trace(String.format("getJdbcUrl: %s", hds.getJdbcUrl()));
		
		log.trace(String.format("getConnectionTimeout: %s", hds.getConnectionTimeout() ));
		log.trace(String.format("getIdleTimeout: %s", hds.getIdleTimeout() ));
		log.trace(String.format("getHikariConfigMXBean: %s", hds.getHikariConfigMXBean() ));
		log.trace(String.format("getHikariPoolMXBean: %s", hds.getHikariPoolMXBean() ));
		log.trace(String.format("getMaxLifetime: %s", hds.getMaxLifetime() ));
		
		log.trace(String.format("getInitializationFailTimeout: %s", hds.getInitializationFailTimeout() ));
		log.trace(String.format("getValidationTimeout: %s", hds.getValidationTimeout() ));
		log.trace(String.format("getTransactionIsolation: %s", hds.getTransactionIsolation() ));
		log.trace(String.format("getUsername: %s", hds.getUsername() ));
		log.trace(String.format("getPassword: %s", hds.getPassword() ));
		log.trace(String.format("getPoolName: %s", hds.getPoolName() ));
		log.trace(String.format("getLoginTimeout: %s", hds.getLoginTimeout() ));
		log.trace(String.format("getDriverClassName: %s", hds.getDriverClassName() ));
		
		log.trace("getDataSourceProperties");
		
		for(Entry<Object, Object> e:hds.getDataSourceProperties().entrySet()) {
			log.trace(String.format("%s -> %s", e.getKey(), e.getValue()));
		}
		
	}
	*/
}
