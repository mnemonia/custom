package com.rocketbrandstudios.gcode.service;

import java.io.File;

public interface Transformer {
	public void transform(File in, File out);
}
