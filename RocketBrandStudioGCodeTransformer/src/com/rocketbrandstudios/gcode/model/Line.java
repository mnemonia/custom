package com.rocketbrandstudios.gcode.model;

public final class Line {
	private String line;
	
	public Line(String line){
		this.line = line;
	}
	
	public String getValue(){
		return line;
	}
	
	@Override
	public String toString(){
		return line + '\n';
	}

}
