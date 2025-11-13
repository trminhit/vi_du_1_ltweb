package vidu_2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vidu_2.config.DBMySQLConnect;
import vidu_2.dao.UserDao;
import vidu_2.model.User;

public class UserDaoimpl implements UserDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public User get(String username) {
		String sql = "SELECT * FROM `user` WHERE username = ? ";
		try {
			conn = DBMySQLConnect.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user; }
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}
	
	@Override
	public void insert(User user) {
	    String sql = "INSERT INTO `user`(username, email, password) VALUES (?, ?, ?)";

	    try {
	        conn = DBMySQLConnect.getConnection();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, user.getUsername());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, user.getPassword());
	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (ps != null) ps.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }
	}
	@Override
	public boolean checkExistEmail(String email) {
	    boolean duplicate = false;
	    String sql = "SELECT * FROM `user` WHERE email = ?";

	    try {
	        conn = DBMySQLConnect.getConnection();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, email);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            duplicate = true; // Có dòng nào khớp email -> email đã tồn tại
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (ps != null) ps.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }

	    return duplicate;
	}
	
	@Override
	public boolean checkExistUsername(String username) {
	    boolean duplicate = false;
	    String sql = "SELECT * FROM `user` WHERE username = ?";

	    try {
	        conn = DBMySQLConnect.getConnection();  
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, username);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            duplicate = true; // có bản ghi -> username đã tồn tại
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (ps != null) ps.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }

	    return duplicate;
	}


}
