package com.rocketbrandstudios.gcode.impl;

import java.io.File;

import com.rocketbrandstudios.gcode.GCodeTransformer;
import com.rocketbrandstudios.gcode.service.TransformerImpl;

public final class GCodeTransformerImpl implements GCodeTransformer {
	private File in,out;
	
	public GCodeTransformerImpl(){
		in = new File("C:/in.gcode");
		out = new File("C:/out.gcode");
	}
	
	@Override
	public void go() {
		new TransformerImpl().transform(in, out);
	}

	@Override
	public void setExportFile(File out) {
		this.out = out;
	}

	@Override
	public void setFValue(int value) {
		System.out.println("setFValue");
	}

	@Override
	public void setImportFile(File file) {
		this.in = file;
	}

	@Override
	public void setSValue(int value) {
		System.out.println("setSValue");
	}

}
