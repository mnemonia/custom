package com.rocketbrandstudios.gcode.service.transformer.transformations;

import com.rocketbrandstudios.gcode.model.Line;
import com.rocketbrandstudios.gcode.model.Lines;
import com.rocketbrandstudios.gcode.service.Transformation;

public final class RemoveLineStartTransformation implements Transformation {
	private final String valueToRemove;
	
	public RemoveLineStartTransformation(String value) {
		this.valueToRemove = value;
	}

	@Override
	public Lines transform(Lines in) {
		for (Line line : in) {
			if(line.getValue().startsWith(valueToRemove)){
				String v = line.getValue();
				v = v.replace(valueToRemove, "");
				v = v.trim();
				Line newLine = new Line(v);
				in.replace(line,newLine);
//				System.out.println("RemoveLineTransformation remove "+valueToRemove);
			}
		}
		return in;
	}

}
