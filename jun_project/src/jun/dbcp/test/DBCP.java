package jun.dbcp.test;

import java.io.IOException;
import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebServlet("/DBCP")
public class DBCP extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Log log = LogFactory.getLog(DBCP.class);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection =null;
		try {
			Context context =new InitialContext( );
			DataSource dataSource =(DataSource) context.lookup("java:comp/env/jdbc");
			connection =dataSource.getConnection( );
			log.info(connection);
			log.info("DB 연결 성공!!");
			
		} catch (Exception e) {
			log.info("DB연결 실패 -" + e);		
		}
		finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
