package com.lzz.bean;

/**
 * @author XL
 *
 */
public class Feel {
	private Integer feelid;
	private Integer userid;
	private String feeltime;
	private String feelcontent;
	private String pic;
	private String speak;

	public String getSpeak() {
		return speak;
	}

	public void setSpeak(String speak) {
		this.speak = speak;
	}

	public Integer getFeelid() {
		return feelid;
	}

	public void setFeelid(Integer feelid) {
		this.feelid = feelid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getFeeltime() {
		return feeltime;
	}

	public void setFeeltime(String feeltime) {
		this.feeltime = feeltime;
	}

	public String getFeelcontent() {
		return feelcontent;
	}

	public void setFeelcontent(String feelcontent) {
		this.feelcontent = feelcontent;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Feel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feel(Integer feelid, Integer userid, String feeltime, String feelcontent, String pic) {
		super();
		this.feelid = feelid;
		this.userid = userid;
		this.feeltime = feeltime;
		this.feelcontent = feelcontent;
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "Feel [feelid=" + feelid + ", userid=" + userid + ", feeltime=" + feeltime + ", feelcontent="
				+ feelcontent + ", pic=" + pic + "]";
	}

}
