<%@page import="edu.cafuc.utils.IntToCategory"%>
<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="utf-8"%>
<%@ page import="edu.cafuc.bean.*" %>    
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>管理员面板</title>
<link media="screen" type="text/css" href="../css/admin-style.css" rel="stylesheet">
<script src="../js/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="../js/simpla.jquery.configuration.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="chrome://translator/skin/floatingPanel.css">
</head>
<body>
<div id="body-wrapper">
  <div id="sidebar">
    <div id="sidebar-wrapper">
      <h1 id="sidebar-title"><a href="#">管理员面板</a></h1>
      <a href="#"><h2>管理员工具</h2></a>
      <div id="profile-links"> 您好, <a title="编辑你的信息" href="#">865171</a> <br>
        <br>
        <a title="View the Site" href="#">查看主站</a> | <a title="Sign Out" href="#">退出</a> </div>
      <ul id="main-nav">
        
        <li> <a class="nav-top-item current" href="#" style="padding-right: 15px;">
          发布新闻</a>
          <ul style="display: none;">
            <li><a href="viewCategory?category=1">考试大纲</a></li>
            <li><a href="viewCategory?category=2">考试计划</a></li>
            <li><a href="viewCategory?category=3">考试安排</a></li>
            <li><a href="viewCategory?category=4">下载专区</a></li>
          </ul>
        </li>
        <li> <a class="nav-top-item" href="#" style="padding-right: 15px;"> 数据统计 </a>
          <ul style="display: none;">
            <li><a href="#">按时间</a></li>
            <li><a href="#">按年级</a></li>
          </ul>
        </li>
        <li> <a class="nav-top-item" href="#" style="padding-right: 15px;"> 其  他 </a>
          <ul style="display: none;">
            <li><a href="#">查看考生</a></li>
            <li><a href="#">查看成绩单</a></li>
            <li><a href="#">上传文件</a></li>
          </ul>
        </li>
        
        
      </ul>
    </div>
  </div>
  <div id="main-content">
  		<% 
    		if(session.getAttribute("notice")!=null){
    			String notice = (String)session.getAttribute("notice");
    	%>
    		<p id="notice">
    			<%=notice %>
    		</p>
   		<%
    		}
  		News news = (News)session.getAttribute("news");
  		%>
    <h2><%=news.getTitle() %></h2>
    <span>发布日期:<%=news.getPublishTime() %></span><span>分类:<%=IntToCategory.transform(news.getCategory()) %></span>
    <p><%=news.getContent() %></p>
   
    <div class="clear"></div>
    	
    <div id="footer"> <small>
      &copy; cafuc | Powered by <a href="#">中国民航飞行学院英语教研室</a> | <a href="#">顶部</a> </small> </div>
  </div>
</div>
</body>
</html>

