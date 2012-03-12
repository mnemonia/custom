package com.rocketbrandstudios.gcode.impl;

import java.io.File;

import com.rocketbrandstudios.gcode.GCodeTransformer;
import com.rocketbrandstudios.gcode.service.TransformerImpl;
import com.rocketbrandstudios.gcode.service.transformer.transformations.ScaleValuesTransformation;

public final class GCodeTransformerImpl implements GCodeTransformer {
	private File in,out;
	private int fScaling = 1;
	private int fFactor = 60;
	private int sScaling = 1;
	private int sFactor = 1;
	
	public GCodeTransformerImpl(){
		in = new File("C:/in.gcode");
		out = new File("C:/out.gcode");
	}
	
	@Override
	public void go() {
		new TransformerImpl(
				new ScaleValuesTransformation("F",fFactor,fScaling),
				new ScaleValuesTransformation("S",sFactor,sScaling)
				).transform(in, out);
	}

	@Override
	public void setExportFile(File out) {
		this.out = out;
	}

	@Override
	public void setFScaling(int value) {
		fScaling = value;
	}

	@Override
	public void setImportFile(File file) {
		this.in = file;
	}

	@Override
	public void setSScaling(int value) {
		sScaling = value;
	}

	@Override
	public void setFFactor(int value) {
		fFactor = value;
	}

	@Override
	public void setSFactor(int value) {
		sFactor = value;
	}

}
