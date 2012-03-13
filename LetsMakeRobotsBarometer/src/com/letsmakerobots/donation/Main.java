package com.letsmakerobots.donation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class Main {

  public static void main(String[] args) throws Exception {
    // Package
    JAXBContext jc = JAXBContext.newInstance(Donations.class, Donation.class);
    Unmarshaller unmarshaller = jc.createUnmarshaller();

//    Donations donations = (Donations) unmarshaller.unmarshal(new URL("http://www.hactotum.nl/donate/paypal_ipn/dump.php?xml=1"));
    Donations donations = (Donations) unmarshaller.unmarshal(Donations.class.getResource("Donations.xml").openStream());    

    for (Donation d : donations.getDonations()) {
        System.out.println("Donation: " + d.getName());
	}
  }
}