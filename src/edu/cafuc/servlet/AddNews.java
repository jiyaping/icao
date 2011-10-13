package edu.cafuc.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cafuc.bean.News;
import edu.cafuc.dao.NewsDao;

/**
 * Servlet implementation class AddNews
 */
public class AddNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNews() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		String title = request.getParameter("title");
		int category = Integer.valueOf(request.getParameter("category"));
		String content = request.getParameter("content");
		Date date = new Date(System.currentTimeMillis());
		News news = new News();
		news.setContent(content);
		news.setCategory(category);
		news.setPublishTime(date);
		news.setTitle(title);
		NewsDao.addNews(news);
		response.sendRedirect("showNews");
	}

}
