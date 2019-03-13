package ddvudo.globalUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseManage {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://www.ddvudo.tk:3306/TestWeb_2018_06_17";
    static final String USER = "webadmin";
    static final String PASS = "liukang951006";
    Connection conn;
    private DataBaseManage() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    }
    public ResultSet doStringSqlQuery(String sql) {
    	Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
    	try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
	            conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	return null;
    }
    
    public boolean doStringSqlInsert(String sql) {
    	Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
    	try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
	            conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	return false;
    }
    public static DataBaseManage getInstance() {
    	return new DataBaseManage();
    }
}
