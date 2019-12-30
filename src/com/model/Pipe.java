package com.model;

public class Pipe {
	private String name;//用户名
	private String password;//密码
	private String bool;//标志
	private String phone;//号码
	private String emild;//邮箱
	private String sex;//性别
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBool() {
		return bool;
	}
	public void setBool(String bool) {
		this.bool = bool;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmild() {
		return emild;
	}
	public void setEmild(String emild) {
		this.emild = emild;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Pipe [name=" + name + ", password=" + password + ", bool=" + bool + ", phone=" + phone + ", emild="
				+ emild + ", sex=" + sex + "]";
	}
	public Pipe() {
		super();
	}
	public Pipe(String name, String password, String bool, String phone, String emild, String sex) {
		super();
		this.name = name;
		this.password = password;
		this.bool = bool;
		this.phone = phone;
		this.emild = emild;
		this.sex = sex;
	}
	
}
