package com.letsmakerobots.donation.barometer.impl;

import com.letsmakerobots.donation.Donations;
import com.letsmakerobots.donation.barometer.DonationBarometer;

public final class DonationVerticalBarChart implements DonationBarometer {

	@Override
	public String create(Donations donations) {
		StringBuffer b = new StringBuffer();
		b.append("<html>\n");
		b.append("  <head>\n");
		b.append("    <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\n");
		b.append("    <script type=\"text/javascript\">\n");
		b.append("      google.load(\"visualization\", \"1\", {packages:[\"columnchart\"]});\n");
		b.append("      google.setOnLoadCallback(drawChart);\n");
		b.append("      function drawChart() {\n");
		b.append("        var data = new google.visualization.DataTable();\n");
		b.append("        data.addColumn('string', 'Project');\n");
		b.append("        data.addColumn('number', 'LMRv4 Development Costs');\n");
		b.append("        data.addColumn('number', 'Your Donations');\n");
		b.append("        data.addRows(1);\n");
		b.append("        data.setValue(0, 0, 'LMRv4');\n");
		b.append("        data.setValue(0, 1, "+donations.getEstimatedTotalAmount()+");\n");
		b.append("        data.setValue(0, 2, "+donations.summarizeDonations()+");\n");
		b.append("\n");
		b.append("        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));\n");
		b.append("        chart.draw(data, {width: 400, height: 240, is3D: false, title: 'Live Status of LMRv4 Donations'});\n");
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
