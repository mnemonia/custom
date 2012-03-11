package com.rocketbrandstudios.gcode.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.rocketbrandstudios.gcode.GCodeTransformer;

public final class GCodeTransformerUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final GCodeTransformer gCodeTransformer;

	public GCodeTransformerUI(GCodeTransformer gCodeTransformer){
		super(new BorderLayout());
		this.gCodeTransformer = gCodeTransformer;
		
		setSize(new Dimension(800, 800));
		addToolBar();
		addParametrisation();
		addStatusBar();
	}

	private void addStatusBar() {
		JPanel statusBar = new JPanel(new BorderLayout());
		add(statusBar,BorderLayout.SOUTH);
		JLabel inFile = new JLabel("In:");
		statusBar.add(inFile,BorderLayout.EAST);
		JProgressBar p = new JProgressBar();
		statusBar.add(p,BorderLayout.CENTER);
		JLabel outFile = new JLabel("Out:");
		statusBar.add(outFile,BorderLayout.WEST);
	}

	private void addParametrisation() {
		// TODO Auto-generated method stub
		
	}

	private void addToolBar() {
		JPanel tb = new JPanel();
		tb.setLayout(new GridLayout(0,1));
		add(tb, BorderLayout.WEST);
		
		JButton select = new JButton("Select Input File ...");
		tb.add(select);
		select.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
			    int returnVal = fc.showOpenDialog(GCodeTransformerUI.this);
				//
			    if (returnVal == JFileChooser.APPROVE_OPTION) {
			        File file = fc.getSelectedFile();
			        gCodeTransformer.setImportFile(file);
			    }
			}
		});
		JButton out = new JButton("Change Output File ...");
		tb.add(out);
		out.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gCodeTransformer.setExportFile();
			}
		});

		JButton go = new JButton("Go!");
		tb.add(go);
		go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gCodeTransformer.go();
			}
		});
	}
}
