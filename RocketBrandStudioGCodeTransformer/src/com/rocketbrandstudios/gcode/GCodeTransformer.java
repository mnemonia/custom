package com.rocketbrandstudios.gcode;

import java.io.File;

public interface GCodeTransformer {
	public void setImportFile(File file);
	public void setExportFile(File file);
	public void go();
	public void setFUpperLimit(int value);
	public void setFFactor(int value);
	public void setFScaling(int value);
	public void setSFactor(int value);
	public void setSScaling(int value);
}
