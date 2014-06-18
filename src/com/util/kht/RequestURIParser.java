package com.util.kht;

import javax.servlet.http.HttpServletRequest;

public class RequestURIParser {
	public static String getAction(HttpServletRequest request){
		String uri = request.getRequestURI();
		int qMark = uri.length();
		int temp = uri.indexOf('?');
		if(temp != -1){
			qMark = temp;
		}
		return uri.substring(uri.lastIndexOf('/')+1, qMark);
	}
	public static String getMapping(HttpServletRequest request){
		String uri = request.getRequestURI();
		return uri.substring(uri.lastIndexOf('.')+1);
	}
}
