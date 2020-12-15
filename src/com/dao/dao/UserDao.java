package com.dao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.dao.dbutil.DBUtil;
import com.dao.query.Query;
import com.data.User;
public class UserDao extends ADao {

	public List<User> getUsers(String sql){
		Connection con = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		List<User> items = new ArrayList<User>();
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			logger.debug("items retrieved");
			
			while(rs.next()) {
				User temp = new User();
				temp.setBill(rs.getInt("bill"));
				temp.setEmail(rs.getString("email"));
				temp.setFirst_name(rs.getString("first_name"));
				temp.setLast_name(rs.getString("last_name"));
				temp.setId(rs.getInt("id"));
				temp.setLogin(rs.getString("login"));
				temp.setPassword(rs.getString("password"));
				temp.setRole_id(rs.getInt("role_id"));
				items.add(temp);
			}
			

		}catch (Exception e){
			logger.error("cannot get items",e);
		}finally {
			close(rs,st,con);
			
		}
		return items;
	}
	public void AddUser(HttpServletRequest request) {
		User user= new User(request.getParameter("login"),request.getParameter("password"),request.getParameter("first_name"),
				request.getParameter("last_name"),request.getParameter("email"),0,Integer.parseInt(request.getParameter("role")));
		Connection conn= null;
		PreparedStatement ps= null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(Query.AddUser);
	         ps.setString(1, user.getLogin());
	         ps.setString(2, user.getPassword());
	         ps.setString(3, user.getFirst_name());
	         ps.setString(4, user.getLast_name());
	         ps.setString(5, user.getEmail());
	         ps.setInt(6, user.getBill());
	         ps.setInt(7, user.getRole_id()); 
	         int rows = ps.executeUpdate();
	         if (rows==1)
	        	 logger.debug("User added");
		} catch (SQLException e) {
			logger.error("Error adding user",e);
		}
		finally {
			close(ps,conn);
		}
		
		 
	}
	public boolean CheckUser(HttpServletRequest request) {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Connection conn= null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		boolean result = false;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(Query.FindUser);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = true;
				logger.debug(rs);
			}
		}catch(Exception e) {
			logger.error("can't identify user",e);
		}
		finally {
			close(rs,ps,conn);
		}
		return result;
	}
	public User GetUser(HttpServletRequest request) {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		User temp = null;
		Connection conn= null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(Query.FindUser);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = new User();
				temp.setBill(rs.getInt("bill"));
				temp.setEmail(rs.getString("email"));
				temp.setFirst_name(rs.getString("first_name"));
				temp.setLast_name(rs.getString("last_name"));
				temp.setId(rs.getInt("id"));
				temp.setLogin(rs.getString("login"));
				temp.setPassword(rs.getString("password"));
				temp.setRole_id(rs.getInt("role_id"));
			}
		}catch(Exception e) {
			logger.error("can't identify user",e);
		}
		finally {
			close(rs,ps,conn);
		}
		return temp;
	}

	
	
}
