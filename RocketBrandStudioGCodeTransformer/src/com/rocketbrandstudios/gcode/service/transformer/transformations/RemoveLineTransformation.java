package com.rocketbrandstudios.gcode.service.transformer.transformations;

import com.rocketbrandstudios.gcode.model.Line;
import com.rocketbrandstudios.gcode.model.Lines;
import com.rocketbrandstudios.gcode.service.Transformation;

public final class RemoveLineTransformation implements Transformation {
	private final String valueToRemove;
	
	public RemoveLineTransformation(String value) {
		this.valueToRemove = value;
	}

	@Override
	public Lines transform(Lines in) {
		for (Line line : in) {
			if(line.toString().contains(valueToRemove)){
				in.remove(line);
				System.out.println("RemoveLineTransformation remove "+valueToRemove);
			}
		}
		return in;
	}

}
