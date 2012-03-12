package com.rocketbrandstudios.gcode.impl;

import java.io.File;

import com.rocketbrandstudios.gcode.GCodeTransformer;
import com.rocketbrandstudios.gcode.service.TransformerImpl;
import com.rocketbrandstudios.gcode.service.transformer.transformations.ScaleValuesTransformation;

public final class GCodeTransformerImpl implements GCodeTransformer {
	private File in,out;
	private int fScaling = 1;
	private int sScaling = 1;
	
	public GCodeTransformerImpl(){
		in = new File("C:/in.gcode");
		out = new File("C:/out.gcode");
	}
	
	@Override
	public void go() {
		System.out.println("GCodeTransformerImpl.go(fScaling:"+fScaling+", sScaling:"+sScaling+")");
		new TransformerImpl(
				new ScaleValuesTransformation("F",60,fScaling),
				new ScaleValuesTransformation("S",1,sScaling)
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
