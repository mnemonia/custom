package com.letsmakerobots.donation.barometer.impl;

import java.util.Collection;

import com.letsmakerobots.donation.Donation;
import com.letsmakerobots.donation.Donations;
import com.letsmakerobots.donation.barometer.DonationBarometer;

public final class DonationPieChart implements DonationBarometer {

	@Override
	public String create(Donations donations) {
		Collection<Donation> byDate = donations.getSummarizeDonationsByDate();
		
		StringBuffer b = new StringBuffer();
		b.append("<html>\n");
		b.append("  <head>\n");
		b.append("    <script type='text/javascript' src='https://www.google.com/jsapi'></script>\n");
		b.append("    <script type='text/javascript'>\n");
		b.append("      google.load('visualization', '1', {packages:['imagepiechart']});\n");
		b.append("      google.setOnLoadCallback(drawChart);\n");
		b.append("      function drawChart() {\n");
		b.append("        var data = new google.visualization.DataTable();\n");
		b.append("        data.addColumn('string', 'Task');\n");
		b.append("        data.addColumn('number', 'Hours per Day');\n");
		b.append("        data.addRows("+(1+byDate.size())+");\n");
		int i = 0;
		for (Donation donation : byDate) {
			b.append("        data.setValue("+i+", 0, '"+donation.getDate()+"');\n");
			b.append("        data.setValue("+i+", 1, "+donation.getAmount()+");\n");
			i++;
		}
		b.append("        data.setValue("+i+", 0, 'Missing');\n");
		b.append("        data.setValue("+i+", 1, "+donations.getEstimatedTotalAmount()+");\n");
		b.append("\n");
		b.append("        var chart = new google.visualization.ImagePieChart(document.getElementById('chart_div'));\n");
		b.append("        chart.draw(data, {width: 430, height: 240, title: 'LMRv4 Donations'});\n");
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
