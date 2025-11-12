package vidu_2.config;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class DBMySQLConnect {

	private static String DB_URL = "jdbc:mysql://localhost:3306/ltwebct4st6";
	private static String USER_NAME = "root";
	private static String PASSWORD = "12345";
	private static Connection con;
	public static Connection getConnection() throws IOException {
		con = null;
		try 
		{
			// driver register
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			con = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

		} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		return (con);
	}
	public static void main(String[] args) 
	{

		try {
			Connection conn = DBMySQLConnect.getConnection();
			String selectAll = "SELECT * FROM GiaoVien";
			// select all GiaoVien
			PreparedStatement stmt = conn.prepareStatement(selectAll);
			// get data from table ‘GiaoVien'
			ResultSet rs = stmt.executeQuery("SELECT * FROM GiaoVien");
			// show data
			while (rs.next()) {
				System.out.println(rs.getInt("id") + " " + rs.getString(2)
				+ " " + rs.getString(3));
			}
			conn.close(); // close connection
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
