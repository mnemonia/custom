package com.rocketbrandstudios.gcode.service.transformer.transformations;

import java.util.StringTokenizer;

import com.rocketbrandstudios.gcode.model.Line;
import com.rocketbrandstudios.gcode.model.Lines;
import com.rocketbrandstudios.gcode.service.Transformation;

public final class ScaleValuesTransformation implements Transformation {
	private final String marker;
	private final int scaling;
	private final int factor;
	private final int upperLimit;
	
	public ScaleValuesTransformation(String marker, int factor, int scaling, int upperLimit) {
		this.factor = factor;
		this.scaling = scaling;
		this.marker = marker;
		this.upperLimit = upperLimit;
	}

	@Override
	public Lines transform(Lines in) {
		for (Line line : in) {
			String value = line.getValue();
			if(!value.contains(marker)){
				continue;
			}

			in.replace(line, transform(line));
		}
		return in;
	}

	private Line transform(Line line) {
		StringBuffer newLineBuffer = new StringBuffer();
		StringTokenizer t = new StringTokenizer(line.getValue()," ",true);
		while(t.hasMoreTokens()){
			String v = t.nextToken();
			if(v.startsWith(marker)){
				newLineBuffer.append(
						marker + 
						calc(
								extractValue(v)));
			}else{
				newLineBuffer.append(v);
			}
		}
		return new Line(newLineBuffer.toString());
	}

	private String extractValue(String v) {
		return v.substring(1,v.length());
	}

	private int calc(String v) {
		double d = Double.parseDouble(v);
		d *= factor;
		d *= scaling / 100.0;
		if(d > upperLimit){
			d = upperLimit;
		}
		return (int)d;
	}
}
