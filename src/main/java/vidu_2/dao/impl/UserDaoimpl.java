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
}
