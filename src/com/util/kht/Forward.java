package com.util.kht;

public class Forward {
	private String path;
	private boolean forwarding = false;
	private String json;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setForwarding(boolean forward) {
		this.forwarding = forward;
	}
	public boolean isForwarding(){
		return forwarding;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
}
