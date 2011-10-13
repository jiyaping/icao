package edu.cafuc.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.cafuc.bean.News;
import edu.cafuc.dao.NewsDao;

public class NewDaoTest {
	public static void main(String args[]){
//		NewsDao nd = new NewsDao();
//		nd.prepareQuery();
//		System.out.println(nd.getTotalPage()+" "+nd.getCurrentPage());
//		List<News> l = nd.getAllNews();
//		for(News ns : l){
//			System.out.println(ns.getId());
//		}
		
		List<News> newsList = new ArrayList<News>();

		News news = new News();
		news.setTitle("1");
		news.setPublishTime(new Date(System.currentTimeMillis()+1000));
		newsList.add(news);
		News news2 = new News();
		news2.setTitle("2");
		news2.setPublishTime(new Date(System.currentTimeMillis()-10000));
		newsList.add(news2);
		System.out.println("pai xu qian");
		for(News n: newsList){
			System.out.println(n.getTitle());
		}
		Collections.sort(newsList,new News());
		System.out.println("pai xu hou");
		for(News n: newsList){
			System.out.println(n.getTitle());
		}
	}
}
