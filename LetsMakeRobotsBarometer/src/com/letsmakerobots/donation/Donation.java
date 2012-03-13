package com.letsmakerobots.donation;

import javax.xml.bind.annotation.XmlElement;

public class Donation {
	private String date;
	private String name;
	private String value;
	
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
	public String getValue() {
		return value;
	}
	@XmlElement
	public void setValue(String value) {
		this.value = value;
	}
}
