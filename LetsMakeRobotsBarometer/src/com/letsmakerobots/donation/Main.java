package com.letsmakerobots.donation;

import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.letsmakerobots.donation.barometer.DonationBarometer;
import com.letsmakerobots.donation.barometer.impl.DonationProgressImpl;

public class Main {

  public static void main(String[] args) throws Exception {
    // Package
    JAXBContext jc = JAXBContext.newInstance(Donations.class, Donation.class);
    Unmarshaller unmarshaller = jc.createUnmarshaller();

    Donations donations = (Donations) unmarshaller.unmarshal(
    		new URL("http://www.hactotum.nl/donate/paypal_ipn/dump.php?xml=1"));
    Donations oldDonations = (Donations) unmarshaller.unmarshal(
    		Donations.class.getResource("Donations.xml"));
    
    List<Donation> ds = new ArrayList<Donation>(oldDonations.getDonations());
    ds.addAll(donations.getDonations());
   
    donations = new Donations();
    donations.setDonations(ds);
    
    DonationBarometer barometer = new DonationProgressImpl(); //new DonationBarometerImpl();
    File out = new File("c:/out.html");
    PrintWriter p = new PrintWriter(out);
    p.print(barometer.create(donations));
    p.close();

  }
}