<<<<<<< HEAD
package jun.spring.project.test;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =  {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ConnectionTest {

	private static final Logger logger =LoggerFactory.getLogger(ConnectionTest.class);
	@Autowired
	DataSource dataSource;
	
	@Test
	public void test() {
		Connection connection=null;
		try {
			connection=dataSource.getConnection();
			logger.info("확인 "+connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
=======
package jun.spring.project.test;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =  {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ConnectionTest {

	private static final Logger logger =LoggerFactory.getLogger(ConnectionTest.class);
	@Autowired
	DataSource dataSource;
	
	@Test
	public void test() {
		Connection connection=null;
		try {
			connection=dataSource.getConnection();
			logger.info("확인 "+connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
>>>>>>> 0fcca063f8cad73f99bf00026c779225a30764b5
