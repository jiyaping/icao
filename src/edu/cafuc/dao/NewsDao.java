package edu.cafuc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.cafuc.bean.News;
import edu.cafuc.utils.ConnUtil;

public class NewsDao {
	private final int pageSize = 12; 
	private int recordCount = 0; 
	private int totalPage = 0; 
	private int currentPage = 1;
	
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	
	//add news
	public static boolean addNews(News news){
		int effectCount = 0;
		String sql = "insert into xinwen(biaoti,riqi,neirong,fenlei) values('"
				+news.getTitle()+"','"+news.getPublishTime()+"','"+news.getContent()+"','"+
				news.getCategory()+"')";
		Connection conn = ConnUtil.getConnection();
		Statement sm;
		try {
			sm = conn.createStatement();
			effectCount = sm.executeUpdate(sql);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(effectCount>0){
			return true;
		}else{
			return false;
		}
	}
	
	//delete news by id
	public static int deleteById(int id){
		int result = 0;
		String sql = "delete from xinwen where id = ?" ;
		Connection conn = ConnUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	//update a news
	public static int updateNews(News news){
		int result = 0;
		String sql = "update xinwen set biaoti=?,riqi=?,neirong=?,fenlei=? where id=?";
		Connection conn = ConnUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, news.getTitle());
			ps.setDate(2, news.getPublishTime());
			ps.setString(3, news.getContent());
			ps.setInt(4, news.getCategory());
			ps.setInt(5, news.getId());
			result = ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	//get news by id
	public static News getNewsById(int id){
		News news = new News();
		ResultSet rs = null;
		String sql = "select * from xinwen where id = "+id;
		Connection conn = ConnUtil.getConnection();
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				news.setTitle(rs.getString("biaoti"));
				news.setPublishTime(rs.getDate("riqi"));
				news.setContent(rs.getString("neirong"));
				news.setCategory(rs.getInt("fenlei"));
				news.setId(rs.getInt("id"));
			}
			
			if(st!=null) st.close();
			if(rs!=null) rs.close();
			if(conn!=null) conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return news;
	}
	
	//get news by category
	public static List<News> getNewsByCategory(int category){
		List<News> newsList = new ArrayList<News>();
		ResultSet rs = null;
		String sql = "select * from xinwen where fenlei = ?";
		Connection conn = ConnUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, category);
			rs = ps.executeQuery();
			while(rs.next()){
				News news = new News();
				news.setTitle(rs.getString("biaoti"));
				news.setPublishTime(rs.getDate("riqi"));
				news.setContent(rs.getString("neirong"));
				news.setCategory(rs.getInt("fenlei"));
				news.setId(rs.getInt("id"));
				newsList.add(news);
			}
			
			if(ps!=null) ps.close();
			if(rs!=null) rs.close();
			if(conn!=null) conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newsList;
	}
	
	//get all news
	public  List<News> getAllNews(){
		List<News> newsList = new ArrayList<News>();
		ResultSet rs = null;
		String sql = "select * from xinwen";
		prepareQuery();
		Connection conn = ConnUtil.getConnection();
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(this.currentPage<1)
				this.currentPage = 1;
			if(this.currentPage>totalPage)
				this.currentPage = totalPage;
			for(int i=0;i<(currentPage-1)*pageSize; i++)
				rs.next();
			for(int i=0; i<pageSize; i++){
				if(rs.next()){
					News news = new News();
					news.setTitle(rs.getString("biaoti"));
					news.setPublishTime(rs.getDate("riqi"));
					news.setContent(rs.getString("neirong"));
					news.setCategory(rs.getInt("fenlei"));
					news.setId(rs.getInt("id"));
					newsList.add(news);
				}else{
					break;
				}
			}
			st.close();
			rs.close();
			conn.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newsList;
	}
	
	//init total page
	public  void prepareQuery(){
		String sql = "select count(*) from xinwen";
		Connection conn = ConnUtil.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				recordCount = rs.getInt(1);
			}
			st.close();
			rs.close();
			conn.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//compute the total page
		totalPage = (recordCount + pageSize - 1)/pageSize;
	}
	
	
	public static void main(String args[]){
		
//		News n = new News();
//		n.setTitle("goodtesttest");
//		n.setCategory(1);
//		n.setPublishTime(new Date(2011, 10, 10));
//		n.setContent("just a test");
//		n.setId(3);
//		boolean isOK = NewsDao.addNews(n);
//		System.out.println(NewsDao.deleteById(1));
//		System.out.println(NewsDao.getNewsById(3));
//		System.out.println(NewsDao.getNewsByCategory(2).size());
//		NewsDao.getSize();
//		System.out.println(NewsDao.updateNews(n));
		
//		List<News> lists = NewsDao.getNewsByCategory(1);
//		for(int i=0; i<lists.size(); i++){
//			System.out.println(lists.get(i));
//		}
		
		NewsDao nd = new NewsDao();
		nd.prepareQuery();
		System.out.println(nd.getTotalPage());
		
	}
}
