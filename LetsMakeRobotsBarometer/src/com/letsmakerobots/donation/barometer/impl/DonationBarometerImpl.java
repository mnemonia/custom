package com.letsmakerobots.donation.barometer.impl;

import com.letsmakerobots.donation.Donations;
import com.letsmakerobots.donation.barometer.DonationBarometer;

public final class DonationBarometerImpl implements DonationBarometer {

	@Override
	public String create(Donations donations) {
		StringBuffer b = new StringBuffer();
		b.append("<html>\n");
		b.append("  <head>\n");
		b.append("    <link rel=\"stylesheet\" type=\"text/css\" href=\"http://visapi-gadgets.googlecode.com/svn/trunk/pilesofmoney/pom.css\"/>\n");
		b.append("    <script type=\"text/javascript\" src=\"http://visapi-gadgets.googlecode.com/svn/trunk/pilesofmoney/pom.js\"></script>\n");
		b.append("    <script type=\"text/javascript\" src=\"http://www.google.com/jsapi\"></script>\n");
		b.append("  </head>\n");
		b.append("  <body>\n");
		b.append("    <div id=\"chartdiv\"></div>\n");
		b.append("    <script type=\"text/javascript\">\n");
		b.append("      google.load(\"visualization\", \"1\");\n");
		b.append("      google.setOnLoadCallback(drawChart);\n");
		b.append("      var chart;\n");
		b.append("      function drawChart() {\n");
		b.append("        var data = new google.visualization.DataTable();\n");
		b.append("        data.addColumn('string', 'Label');\n");
		b.append("        data.addColumn('number', 'Value');\n");
		b.append("        data.addRows(2);\n");
		b.append("        data.setCell(0, 0, 'Your Donations');\n");
		b.append("        data.setCell(1, 0, 'LMRv4 Development Costs');\n");
		b.append("        data.setCell(0, 1, 10, '$"+donations.summarizeDonations()+"');\n");
		b.append("        data.setCell(1, 1, 30, '$30,000');\n");
		b.append("        var chartDiv = document.getElementById('chartdiv');\n");
		b.append("        var options = {title: 'LMRv4 Donation Barometer'};\n");
		b.append("        chart = new PilesOfMoney(chartDiv);\n");
		b.append("        chart.draw(data, options);\n");
		b.append("        //google.visualization.events.addListener(chart, 'select', handleSelect);\n");
		b.append("      }\n");
		b.append("    </script>\n");
		b.append("  </body>\n");
		b.append("</html>\n");
		return b.toString();
	}

}
