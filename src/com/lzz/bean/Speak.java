package com.lzz.bean;

public class Speak {
	private Integer speakid;
	private String context;
	private String time;
	private Integer userid;
	private Integer fid;
	private Integer pid;
	public Integer getSpeakid() {
		return speakid;
	}
	public void setSpeakid(Integer speakid) {
		this.speakid = speakid;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Speak(Integer pid) {
		super();
		this.pid = pid;
	}
	public Speak() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Speak [speakid=" + speakid + ", context=" + context + ", time=" + time + ", userid=" + userid + ", fid="
				+ fid + ", pid=" + pid + "]";
	}
}
