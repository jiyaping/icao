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
 * Servlet implementation class UpdateNews
 */
public class UpdateNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateNews() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		News news = NewsDao.getNewsById(id);
		request.setAttribute("news", news);
		request.getRequestDispatcher("editNews.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		News news = new News();
		news.setId(Integer.valueOf(request.getParameter("id")));
		news.setCategory(Integer.valueOf(request.getParameter("category")));
		news.setTitle(request.getParameter("title"));
		news.setPublishTime(new Date(System.currentTimeMillis()));
		news.setContent(request.getParameter("content"));
		int result = NewsDao.updateNews(news);
		if(result>0){
			request.setAttribute("notice", "更新成功");
		}else{
			request.setAttribute("notice", "更新出错");
		}
		request.getSession().setAttribute("news", news);
		request.getRequestDispatcher("viewNews.jsp").forward(request, response);
	}

}
