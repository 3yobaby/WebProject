package com.myweb.application;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.myweb.database.cafe.CafeDB;

public class CafeSearch{
	static private CafeSearch instance;
	static public CafeSearch getInstance(){
		if(instance == null){
			return instance = new CafeSearch();
		}
		else return instance;
	}
	private ArrayList<JSONObject> cafes;
	private CafeSearch() {
		setInitCafe();
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while(true){
//					setInitCafe();
//					try {
//						Thread.sleep(10000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
	}
	public void setInitCafe(){
		cafes = new ArrayList<JSONObject>();
		CafeDB db = new CafeDB();
		JSONArray array = db.getNewCafeInfoList(20);
		for(int i=0; i<array.size(); i++){
			JSONObject json = (JSONObject)array.get(i);
			cafes.add(json);
		}
		db.close();
	}
	public ArrayList<JSONObject> getInitCafes(){
		setInitCafe();
		return cafes;
	}
}