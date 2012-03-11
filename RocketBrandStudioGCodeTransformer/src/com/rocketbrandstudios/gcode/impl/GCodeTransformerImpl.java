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
		System.out
		.println("Go");
		new TransformerImpl().transform(in, out);
	}

	@Override
	public void setExportFile() {
		System.out.println("setExportFile");
//		this.out = out;
	}

	@Override
	public void setFValue(int value) {
		System.out.println("setFValue");
	}

	@Override
	public void setImportFile(File file) {
		System.out.println("setImportFile: "+file.getAbsolutePath());
		this.in = file;
	}

	@Override
	public void setSValue(int value) {
		System.out.println("setSValue");
	}

}
