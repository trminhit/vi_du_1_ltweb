package vidu_2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vidu_2.config.DBMySQLConnect;
import vidu_2.dao.CategoryDao;
import vidu_2.model.Category;

public class CategoryDaoimpl implements CategoryDao{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;


	@Override
	public void insert(Category category) {
		String sql = "INSERT INTO Category(catename, icon) VALUES (?,?)";
		try {
			conn = DBMySQLConnect.getConnection(); //
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCatename());
			ps.setString(2, category.getIcon());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
	}
	@Override
	public void edit(Category category) {
		String sql = "UPDATE Category SET catename = ?, icon = ? WHERE cateid = ?";
		try {
			conn = DBMySQLConnect.getConnection(); //
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCatename());
			ps.setString(2, category.getIcon());
			ps.setInt(3, category.getCateid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Category WHERE cateid = ?";
		try {
			conn = DBMySQLConnect.getConnection(); //
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
	}

	@Override
	public Category get(int id) {
		String sql = "SELECT * FROM Category WHERE cateid = ? ";
		try {
			conn = DBMySQLConnect.getConnection(); //
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCateid(rs.getInt("cateid"));
				category.setCatename(rs.getString("catename"));
				category.setIcon(rs.getString("icon"));
				return category;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return null;
	}

	@Override
	public List<Category> getAll() {
		List<Category> categories = new ArrayList<Category>();
		String sql = "SELECT * FROM Category";
		try {
			conn = DBMySQLConnect.getConnection(); //
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCateid(rs.getInt("cateid"));
				category.setCatename(rs.getString("catename"));
				category.setIcon(rs.getString("icon"));
				categories.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return categories;
	}

	@Override
	public Category get(String name) {
		String sql = "SELECT * FROM Category WHERE catename = ? ";
		try {
			conn = DBMySQLConnect.getConnection(); //
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCateid(rs.getInt("cateid"));
				category.setCatename(rs.getString("catename"));
				category.setIcon(rs.getString("icon"));
				return category;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return null;
	}

	@Override
	public List<Category> search(String keyword) {
		List<Category> categories = new ArrayList<Category>();
		String sql = "SELECT * FROM Category WHERE catename LIKE ?";
		try {
			conn = DBMySQLConnect.getConnection(); //
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCateid(rs.getInt("cateid"));
				category.setCatename(rs.getString("catename"));
				category.setIcon(rs.getString("icon"));
				categories.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return categories;
	}
}