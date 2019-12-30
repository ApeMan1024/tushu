package com.model;

public class Card {
	private String number;//借书证号
	private String name;//姓名
	private String dpt;//系别
	private String career;//专业
	private int    amount;//允许借阅量
	private int    total;//总分
	private int    owe;//欠分
	private String phone;
	private String gender; 
	public Card() {
		super();
	}
	
	
	


	public Card(String number, String name, String dpt, String career, int amount, int total, int owe, String phone,
			String gender) {
		super();
		this.number = number;
		this.name = name;
		this.dpt = dpt;
		this.career = career;
		this.amount = amount;
		this.total = total;
		this.owe = owe;
		this.phone = phone;
		this.gender = gender;
	}





	public String getGender() {
		return gender;
	}





	public void setGender(String gender) {
		this.gender = gender;
	}





	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDpt() {
		return dpt;
	}
	public void setDpt(String dpt) {
		this.dpt = dpt;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getOwe() {
		return owe;
	}
	public void setOwe(int owe) {
		this.owe = owe;
	}
	@Override
	public String toString() {
		return "Card [number=" + number + ", name=" + name + ", dpt=" + dpt + ", career=" + career + ", amount="
				+ amount + ", total=" + total + ", owe=" + owe + "]";
	}
	
}
