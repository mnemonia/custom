package com.rocketbrandstudios.gcode;

import com.rocketbrandstudios.gcode.service.Transformation;


public interface GCodeTransformer {
	public GCodeTransformer transform(String fName);
	public GCodeTransformer into(String fName);
	public GCodeTransformer with(Transformation... transformation);
	public void go();
}
