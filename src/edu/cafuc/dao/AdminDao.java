package edu.cafuc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.cafuc.bean.Admin;
import edu.cafuc.utils.ConnUtil;

public class AdminDao {
	//check this admin is exists
	public static boolean checkAdmin(Admin admin){
		boolean isAdmin = false;
		String sql = "select * from guanliyuan where xingming=? and mima=?";
		Connection conn = ConnUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, admin.getUsername());
			ps.setString(2, admin.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				isAdmin = true;
			}
			if(ps!=null) ps.close();
			if(rs!=null) rs.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isAdmin;
	}
	
	//list all admin
	public static List<Admin> listAdmin(){
		List<Admin> adminList = new ArrayList<Admin>();
		String sql = "select * from guanliyuan";
		Connection conn = ConnUtil.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Admin admin = new Admin();
				admin.setUsername(rs.getString("xingming"));
				admin.setPassword(rs.getString("mima"));
				admin.setId(rs.getInt("id"));
				adminList.add(admin);
			}
			if(st!=null) st.close();
			if(rs!=null) rs.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return adminList;
	}
	
	//update admin
	public static int update(Admin admin){
		String sql = "update guanliyuan set xingming=?,password=? where id=?";
		Connection conn = ConnUtil.getConnection();
		int effectCount = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, admin.getUsername());
			ps.setString(2, admin.getPassword());
			ps.setInt(3, admin.getId());
			effectCount = ps.executeUpdate();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return effectCount;
	}
	
	public static void main(String args[]){
		List<Admin> lists = AdminDao.listAdmin();
		for(int i=0; i<lists.size(); i++){
			System.out.println(lists.get(i));
		}
		
//		Admin admin = new Admin();
//		admin.setPassword("123321");
//		admin.setUsername("xie");
//		if(AdminDao.checkAdmin(admin)){
//			System.out.println("yes");
//		}
	}
}
