package edu.cafuc.utils;

public class IntToCategory {
	public static String transform(int category){
		String str;
		switch (category) {
		case 1:
			str = "考试大纲";
			break;
		case 2:
			str = "考试计划";
			break;
		case 3:
			str = "考试安排";
			break;
		case 4:
			str = "下载专区";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}
}
