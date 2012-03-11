package com.rocketbrandstudios.gcode.service;

import com.rocketbrandstudios.gcode.model.Lines;

public interface Transformation {
	public Lines transform(Lines in);
}
