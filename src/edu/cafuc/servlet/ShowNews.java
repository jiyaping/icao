package edu.cafuc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.cafuc.dao.NewsDao;

public class ShowNews extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int currentPage;
		HttpSession session = req.getSession();
		
		if(req.getParameter("currentPage")==null){
			currentPage = 1;
		}else{
			currentPage = Integer.valueOf(req.getParameter("currentPage"));
		}
		NewsDao newsDao = new NewsDao();
		newsDao.prepareQuery();
		newsDao.setCurrentPage(currentPage);
		session.setAttribute("newsDao", newsDao);
//		System.out.println("in shownews doget "+ newsDao.getTotalPage());
		resp.sendRedirect("index.jsp");
	}
}
