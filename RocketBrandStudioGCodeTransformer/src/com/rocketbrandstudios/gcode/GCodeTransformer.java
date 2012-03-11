package com.rocketbrandstudios.gcode;

import java.io.File;

public interface GCodeTransformer {
	public void setImportFile(File file);
	public void setExportFile(File file);
	public void go();
	public void setFValue(int value);
	public void setSValue(int value);
}
