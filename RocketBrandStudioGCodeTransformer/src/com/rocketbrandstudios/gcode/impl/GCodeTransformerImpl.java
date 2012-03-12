package com.rocketbrandstudios.gcode.impl;

import java.io.File;

import com.rocketbrandstudios.gcode.GCodeTransformer;
import com.rocketbrandstudios.gcode.service.TransformerImpl;
import com.rocketbrandstudios.gcode.service.transformer.transformations.ScaleValuesTransformation;

public final class GCodeTransformerImpl implements GCodeTransformer {
	private File in,out;
	private int fScaling;
	private int sScaling;
	
	public GCodeTransformerImpl(){
		in = new File("C:/in.gcode");
		out = new File("C:/out.gcode");
	}
	
	@Override
	public void go() {
		
		new TransformerImpl(
				new ScaleValuesTransformation("F",60),
				new ScaleValuesTransformation("S",1)
				).transform(in, out);
	}

	@Override
	public void setExportFile(File out) {
		this.out = out;
	}

	@Override
	public void setFValue(int value) {
		fScaling = value;
	}

	@Override
	public void setImportFile(File file) {
		this.in = file;
	}

	@Override
	public void setSValue(int value) {
		sScaling = value;
	}

}
