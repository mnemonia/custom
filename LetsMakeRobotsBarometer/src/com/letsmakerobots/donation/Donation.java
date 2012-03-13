package com.letsmakerobots.donation;

import javax.xml.bind.annotation.XmlElement;

public class Donation {
	private String date;
	private String name;
	private String amount;
	
	public String getDate() {
		return date;
	}
	@XmlElement
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public String getAmount() {
		return amount;
	}
	@XmlElement
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
