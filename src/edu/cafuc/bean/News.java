package edu.cafuc.bean;

import java.sql.Date;

public class News {
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
	
	@Override
	public String toString() {
		return "id="+id+" title="+title +" content="+content+"....";
	}
}


