package com.rocketbrandstudios.gcode.service.transformer.transformations;

import java.util.StringTokenizer;

import com.rocketbrandstudios.gcode.model.Line;
import com.rocketbrandstudios.gcode.model.Lines;
import com.rocketbrandstudios.gcode.service.Transformation;

public final class ScaleValuesTransformation implements Transformation {
	private final String marker;
	private final int scaling;
	
	public ScaleValuesTransformation(String marker, int scaling) {
		this.scaling = scaling;
		this.marker = marker;
	}

	@Override
	public Lines transform(Lines in) {
		for (Line line : in) {
			String value = line.getValue();
			if(!value.contains(marker)){
				continue;
			}

			StringBuffer newLineBuffer = new StringBuffer();
			StringTokenizer t = new StringTokenizer(line.getValue()," ",true);
			while(t.hasMoreTokens()){
				String v = t.nextToken();
				if(v.startsWith(marker)){
					v = v.substring(1,v.length());
					double d = Double.parseDouble(v);
					d = scaling * d;
					newLineBuffer.append(marker+d);
				}else{
					newLineBuffer.append(v);
				}
			}

			in.replace(line, new Line(newLineBuffer.toString()));
		}
		return in;
	}
}
