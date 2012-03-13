package com.letsmakerobots.donation;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Donations {
	private Collection<Donation> donations = new ArrayList<Donation>();

	public Collection<Donation> getDonations() {
		return donations;
	}

	@XmlElement
	public void setDonations(Collection<Donation> donations) {
		this.donations = donations;
	}
}
