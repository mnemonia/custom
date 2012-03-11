package com.rocketbrandstudios.gcode.service.transformer.transformations;

import com.rocketbrandstudios.gcode.model.Line;
import com.rocketbrandstudios.gcode.model.Lines;
import com.rocketbrandstudios.gcode.service.Transformation;

public class ReplaceLineStartTransformation implements Transformation {
	private final String search;
	private final String replace;
	
	public ReplaceLineStartTransformation(String search, String replace) {
		this.search = search;
		this.replace = replace;
	}

	@Override
	public Lines transform(Lines in) {
		for (Line line : in) {
			if(line.getValue().startsWith(search)){
				String v = line.getValue();
				v = v.replace(search, replace);
				Line newLine = new Line(v);
				in.replace(line,newLine);
				System.out.println("ReplaceLineStartTransformation replace "+search+" with "+replace);
			}
		}
		return in;
	}

}
