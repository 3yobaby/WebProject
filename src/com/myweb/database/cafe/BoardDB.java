package com.myweb.database.cafe;

import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.util.kht.DatabaseConnector;

public class BoardDB extends DatabaseConnector{
//	  pk number primary key,
//	  fk_category number,
//	  fk_member number,
//	  name varchar2(20),
//	  title varchar2(20),
//	  content varchar2(4000),
//	  created date,
//	  ref number

	public int insert(int pk_category, int pk_member, String name,
			String title, String content) {
		setSql("insert into board values(seq_cafe_board.nextval, "
				+ " ?, ?, ?, ?, ?, sysdate, seq_cafe_board.nextval )");
		setInt(1, pk_category);
		setInt(2, pk_member);
		setString(3, name);
		setString(4, title);
		setString(5, content);
		return insertData();
	}
	
	public int delete(int pk, int member_pk) {
		setSql("delete from board where pk=? and fk_member=?");
		setInt(1, pk);
		setInt(2, member_pk);
		return deleteData();
	}
	
	public JSONObject getBoard(int boardKey){
		setSql("select * from board where pk=?");
		setInt(1, boardKey);
		return getJSONObject();
	}
	
	public JSONArray getBoards(int categoryKey) {
		setSql("select * from board where fk_category =? order by ref desc");
		setInt(1, categoryKey);
		return getJSONArray();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected JSONObject getJSONObjectFromResultset(ResultSet rs) {
		JSONObject json = new JSONObject();
		try{
			json.put("pk",rs.getInt("pk"));
			json.put("fk_category", rs.getInt("fk_category"));
			json.put("fk_member", rs.getInt("fk_member"));
			json.put("name", rs.getString("name"));
			json.put("title", rs.getString("title"));
			json.put("content", rs.getString("content"));
			json.put("created", rs.getDate("created").toString());
			json.put("ref", rs.getInt("ref"));
			return json;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}

}
