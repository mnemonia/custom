package com.rocketbrandstudios.gcode.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.rocketbrandstudios.gcode.model.Line;
import com.rocketbrandstudios.gcode.model.Lines;
import com.rocketbrandstudios.gcode.service.transformer.transformations.RemoveLineStartTransformation;
import com.rocketbrandstudios.gcode.service.transformer.transformations.RemoveLineTransformation;
import com.rocketbrandstudios.gcode.service.transformer.transformations.ReplaceLineStartTransformation;
import com.rocketbrandstudios.gcode.service.transformer.transformations.ScaleValuesTransformation;

public final class TransformerImpl implements Transformer {
	private final List<Transformation> transformations = new ArrayList<Transformation>();
	
	
	public TransformerImpl(){
		transformations.add(new RemoveLineTransformation("M104"));
		transformations.add(new RemoveLineTransformation("M105"));
		transformations.add(new RemoveLineTransformation("M106"));
		transformations.add(new RemoveLineTransformation("M107"));
		transformations.add(new RemoveLineTransformation("M140"));
		transformations.add(new RemoveLineTransformation("M141"));
		transformations.add(new RemoveLineTransformation("M142"));
		transformations.add(new RemoveLineTransformation("M113"));
		transformations.add(new RemoveLineStartTransformation("M108"));
		transformations.add(new ReplaceLineStartTransformation("M101", "M8"));
		transformations.add(new ReplaceLineStartTransformation("M103", "M9"));
		transformations.add(new ScaleValuesTransformation("F",60));
		transformations.add(new ScaleValuesTransformation("S",60));
	}
	
	@Override
	public void transform(File in, File out) {
		Lines content = readContent(in);

		for (Transformation transformation : transformations) {
			content = transformation.transform(content);
		}
		
		writeContent(content,out);
	}

	private void writeContent(Lines content, File out) {
		PrintWriter f;
		try {
			f = new PrintWriter(out);
			for (Line line : content) {
				f.println(line.getValue());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Lines readContent(File in) {
		Lines lines = new Lines();
		try {
			FileReader fr = new FileReader(in); 
			BufferedReader br = new BufferedReader(fr); 
			String s;
			while((s = br.readLine()) != null) { 
				lines.add(new Line(s));
			} 
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return lines;
	}

}
