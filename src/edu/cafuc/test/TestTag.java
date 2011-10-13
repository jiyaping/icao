package edu.cafuc.test;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TestTag extends TagSupport {
	private String params = "你好";
	public void setParams(String params) {
		this.params = params;
	}
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.println("<h2>Goood jooooob !!" + params+"</h2>");
			out.println("test tag");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}
}
