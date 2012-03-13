package com.letsmakerobots.donation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Donations {
	private Collection<Donation> donations = new ArrayList<Donation>();

	@XmlElement(name = "donation")
	public Collection<Donation> getDonations() {
		return donations;
	}

	public void setDonations(Collection<Donation> donations) {
		this.donations = donations;
	}
	
	public Collection<Donation> getByDate(){
		Comparator<Donation> c = new Comparator<Donation>() {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			@Override
			public int compare(Donation d1, Donation d2) {
				try {
					Date date1 = dateFormat.parse(d1.getDate());
					Date date2 = dateFormat.parse(d2.getDate());
					return date1.compareTo(date2);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
		};
		List<Donation> sortedDonations = new ArrayList<Donation>(donations);
		Collections.sort(sortedDonations,c);
		return sortedDonations;
	}
	
	public Collection<Donation> summarizeDonationsByDate(){
		Map<String,Double> map = new HashMap<String, Double>();
		for (Donation d : getByDate()) {
			if(!map.containsKey(d.getDate())){
				map.put(d.getDate(), 0.0);
			}
			
			map.put(d.getDate(), 
					Double.parseDouble(d.getAmount()) 
					+ map.get(d.getDate()));
		}
		
		Collection<Donation> don = new ArrayList<Donation>();
		for (Entry<String, Double> e : map.entrySet()) {
			Donation d = new Donation();
			d.setDate(e.getKey());
			d.setAmount(""+e.getValue());
			don.add(d);
		}
		return don;
	}
	
	public int summarizeDonations(){
		double sum = 0;
		for (Donation d : donations) {
			sum += Double.parseDouble(d.getAmount());
		}
		return (int)sum;
	}
	
}
