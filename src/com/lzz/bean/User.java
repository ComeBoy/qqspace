package com.lzz.bean;


public class User {
	private Integer userid;
	private String qq;
	private String username;
	private String password;
	private String headpic;
	private String sex;
	private String birthday;
	private String birthdayString;
	public String getBirthdayString() {
		return birthdayString;
	}
	public void setBirthdayString(String birthdayString) {
		this.birthdayString = birthdayString;
	}
	private String nowaddress;
	private String hometown;
	private String ismarry;
	private String state;
	private String lastvisit;
	public String getLastvisit() {
		return lastvisit;
	}
	public void setLastvisit(String lastvisit) {
		this.lastvisit = lastvisit;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadpic() {
		return headpic;
	}
	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getNowaddress() {
		return nowaddress;
	}
	public void setNowaddress(String nowaddress) {
		this.nowaddress = nowaddress;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getIsmarry() {
		return ismarry;
	}
	public void setIsmarry(String ismarry) {
		this.ismarry = ismarry;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", qq=" + qq + ", username=" + username + ", password=" + password
				+ ", headpic=" + headpic + ", sex=" + sex + ", birthday=" + birthday + ", birthdayString="
				+ birthdayString + ", nowaddress=" + nowaddress + ", hometown=" + hometown + ", ismarry=" + ismarry
				+ ", state=" + state + ", lastvisit=" + lastvisit + "]";
	}
}
