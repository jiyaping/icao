package edu.cafuc.bean;

import java.sql.Date;
import java.util.Comparator;

public class News implements Comparator<News>{
	private String title;
	private String content;
	private Date publishTime;
	private int id;
	private int category;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	
	public String shortContent(){
		if(content==null){
			content = "";
		}
		if(content.length()>20){
			return content.substring(0, 20)+" ... ";
		}else{
			return content;
		}
	}
	@Override
	public String toString() {
		return "id="+id+" title="+title +" content="+content+"....";
	}
	@Override
	public int compare(News o1, News o2) {
		return o1.getPublishTime().compareTo(o2.getPublishTime());
	}
}


