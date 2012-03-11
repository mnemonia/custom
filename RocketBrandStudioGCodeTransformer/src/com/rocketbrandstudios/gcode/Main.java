package com.rocketbrandstudios.gcode;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.rocketbrandstudios.gcode.impl.GCodeTransformerImpl;
import com.rocketbrandstudios.gcode.ui.GCodeTransformerUI;

public final class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Rocket Brand Studios GCode Transformer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 800));
		frame.setMinimumSize(new Dimension(800,600));
		
		GCodeTransformer t = new GCodeTransformerImpl();
		frame.add(new GCodeTransformerUI(t));
		frame.setVisible(true);
		
	}

}
