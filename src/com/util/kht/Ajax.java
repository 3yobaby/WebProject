package com.util.kht;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class Ajax {
	public abstract void execute(String command, HttpServletRequest request, HttpServletResponse response);
	
	protected void submit(boolean b, HttpServletResponse response){
		PrintWriter out;
		try {
			out = response.getWriter();
			if(b){
				out.print("true");
			}else{
				out.print("false");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void submit(JSONObjectAble json, HttpServletResponse response){
		if(json == null)
			submit(new JSONObject().toString(), response);
		else submit(json.toString(), response);
	}
	
	protected void submit(JSONArray json, HttpServletResponse response){
		if(json == null)
			submit(new JSONArray().toString(), response);
		else submit(json.toString(), response);
	}
	
	protected void submit(JSONObject json, HttpServletResponse response){
		if(json == null)
			submit(new JSONObject().toString(), response);
		else submit(json.toString(), response);
	}
	
	protected void submit(String command, HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(command);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
