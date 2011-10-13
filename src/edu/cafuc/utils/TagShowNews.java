package edu.cafuc.utils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import edu.cafuc.bean.News;
import edu.cafuc.dao.NewsDao;

public class TagShowNews extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int category;
	private int number=5;
	
	public void setNumber(int number) {
		this.number = number;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	@Override
	public int doEndTag() throws JspException {
		List<News> news = null;
		NewsDao nd = new NewsDao();
		if(category>0){
			news = NewsDao.getNewsByCategory(category);
		}else{
			news = nd.getAllNews();
			number += 3;
		}
		Collections.sort(news, new News());
		if(news.size()<number){
			number = news.size();
		}
		JspWriter out = pageContext.getOut();
		try {
			out.println("<div id=category"+category+">");
			News tempNew = null;
			for(int i=0; i<number; i++){
				tempNew = news.get(i);
				out.println("<p id="+tempNew.getId()+"><span>");
				out.println("<a href='../showNew.jsp?id="+tempNew.getId()+"'>"+tempNew.getTitle()+"</a>");
				out.println("</span><span>");
				out.println(tempNew.getPublishTime());
				out.println("</span></p>");
			}
			out.println("<div>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doEndTag();
	}
}
