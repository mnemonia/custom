package com.rocketbrandstudios.gcode.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.rocketbrandstudios.gcode.GCodeTransformer;
import com.rocketbrandstudios.gcode.service.Transformation;
import com.rocketbrandstudios.gcode.service.TransformerImpl;

public final class GCodeTransformerImpl implements GCodeTransformer {
	private File in,out;

	private Collection<Transformation> additionalTransformations = new ArrayList<Transformation>();

	public GCodeTransformerImpl(){;}
	
	@Override
	public void go() {
		new TransformerImpl(
				additionalTransformations
				).transform(in, out);
	}

	@Override
	public GCodeTransformer transform(String fileName) {
		this.in = new File(fileName);
		return this;
	}

	@Override
	public GCodeTransformer into(String fileName) {
		this.out = new File(fileName);
		return this;
	}

	@Override
	public GCodeTransformer with(Transformation... transformation) {
		for (Transformation t : transformation) {
			additionalTransformations.add(t);
		}
		return this;
	}

}
