package com.rocketbrandstudios.gcode.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Lines implements Iterable<Line> {
	private List<Line> lines = new ArrayList<Line>();

	@Override
	public Iterator<Line> iterator() {
		return new ArrayList<Line>(lines).iterator();
	}
	
	public void remove(Line line){
		lines.remove(line);
	}
	
	public void add(Line line){
		lines.add(line);
	}
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		for (Line line : lines) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	public void replace(Line line, Line newLine) {
		int index = lines.indexOf(line);
		lines.remove(line);
		lines.add(index, newLine);
	}

	public void insert(Line line, int index) {
		lines.add(index, line);
	}
}
