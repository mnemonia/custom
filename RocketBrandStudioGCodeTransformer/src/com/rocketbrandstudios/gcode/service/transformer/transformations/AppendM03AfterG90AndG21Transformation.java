package com.rocketbrandstudios.gcode.service.transformer.transformations;

import com.rocketbrandstudios.gcode.model.Line;
import com.rocketbrandstudios.gcode.model.Lines;
import com.rocketbrandstudios.gcode.service.Transformation;

public final class AppendM03AfterG90AndG21Transformation implements
		Transformation {

	@Override
	public Lines transform(Lines in) {
		Line lineG90 = null;
		int indexG90 = -1;
		Line lineG21 = null;
		int indexG21 = -1;
		
		int index = 0;
		for (Line line : in) {
			if(line.getValue().startsWith("G90")){
				lineG90 = line;
				indexG90 = index;
			}else if(line.getValue().startsWith("G21")){
				lineG21 = line;
				indexG21 = index; 
			}
			
			if(lineG90 != null && lineG21 != null){
				if(indexG21-indexG90 == 1){
					in.insert(new Line("M03"),index+1);
					return in;
				}
				
			}
			index++;
		}
		return in;
	}

}
