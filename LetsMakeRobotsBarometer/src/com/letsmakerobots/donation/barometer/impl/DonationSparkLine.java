package com.letsmakerobots.donation.barometer.impl;

import java.util.Collection;

import com.letsmakerobots.donation.Donation;
import com.letsmakerobots.donation.Donations;
import com.letsmakerobots.donation.barometer.DonationBarometer;

public final class DonationSparkLine implements DonationBarometer {

	@Override
	public String create(Donations donations) {
		StringBuffer b = new StringBuffer();
		b.append("<html>\n");
		b.append("  <head>\n");
		b.append("    <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\n");
		b.append("    <script type=\"text/javascript\">\n");
		b.append("      google.load(\"visualization\", \"1\", {packages:[\"imagesparkline\"]});\n");
		b.append("      google.setOnLoadCallback(drawChart);\n");
		b.append("\n");
		b.append("      function drawChart() {\n");
		b.append("        var data = new google.visualization.DataTable();\n");
		b.append("        data.addColumn(\"number\", \"Your Donations\");\n");
		Collection<Donation> byDate = donations.getCumulativeDonationsByDate();
		b.append("        data.addRows("+byDate.size()+");\n");
		int i = 0;
		for (Donation donation : byDate) {
			
			b.append("        data.setValue("+i+",0,"+donation.getAmount()+");\n");
			i++;
		}
		b.append("\n");
		b.append("        var chart = new google.visualization.ImageSparkLine(document.getElementById('chart_div'));\n");
		b.append("        chart.draw(data, {width: 120, height: 40, showAxisLines: false,  showValueLabels: false, labelPosition: 'left'});\n");
		b.append("      }\n");
		b.append("    </script>\n");
		b.append("  </head>\n");
		b.append("\n");
		b.append("  <body>\n");
		b.append("    <div id=\"chart_div\"></div>\n");
		b.append("  </body>\n");
		b.append("</html>\n");		
		return b.toString();
	}

}
