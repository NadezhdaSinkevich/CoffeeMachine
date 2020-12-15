package com.dao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.dbutil.DBUtil;
import com.data.Ingredient;

public class IngredientDao extends ADao{
	public List<Ingredient> getIngredients(String sql){
		Connection con = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		List<Ingredient> items = new ArrayList<Ingredient>();
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			logger.debug("items retrieved");
			
			while(rs.next()) {
				Ingredient temp = new Ingredient();
				temp.setCount(rs.getInt("count"));
				temp.setId(rs.getInt("id"));
				temp.setName(rs.getString("name"));
				temp.setPrice(rs.getInt("price"));
				items.add(temp);
			}
			

		}catch (Exception e){
			logger.error("cannot get items",e);
		}finally {
			close(rs,st,con);
			
		}
		return items;
	}
}
