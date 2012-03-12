package com.rocketbrandstudios.gcode.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		JPanel parametrisation = new JPanel(new GridLayout(1,2));
		add(parametrisation,BorderLayout.CENTER);

		JPanel param = new JPanel(new BorderLayout());
		parametrisation.add(param);
		
		JLabel fLabel = new JLabel("Adjust F Value", JLabel.CENTER);
		fLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		param.add(fLabel, BorderLayout.NORTH);
		final JSlider f = new JSlider(JSlider.VERTICAL,0,200,100);
		param.add(f, BorderLayout.CENTER);
		f.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				gCodeTransformer.setFValue((int)f.getValue());
			}
		});
		f.setMajorTickSpacing(10);
		f.setPaintTicks(true);
		Hashtable labelTable = new Hashtable();
		labelTable.put( new Integer( 0 ), new JLabel("0%") );
		labelTable.put( new Integer( 100 ), new JLabel("100%") );
		labelTable.put( new Integer( 200 ), new JLabel("200%") );
		f.setLabelTable( labelTable );
		f.setPaintLabels(true);
		
		param = new JPanel(new BorderLayout());
		parametrisation.add(param);
		
		JLabel sLabel = new JLabel("Adjust S Value", JLabel.CENTER);
		fLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		param.add(sLabel, BorderLayout.NORTH);
		final JSlider s = new JSlider(JSlider.VERTICAL,0,200,100);
		param.add(s);
		
		s.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				gCodeTransformer.setSValue((int)s.getValue());
			}
		});
		s.setMajorTickSpacing(10);
		s.setPaintTicks(true);
		labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( 0 ), new JLabel("0%") );
		labelTable.put( new Integer( 100 ), new JLabel("100%") );
		labelTable.put( new Integer( 200 ), new JLabel("200%") );
		s.setLabelTable( labelTable );
		s.setPaintLabels(true);
		
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
				final JFileChooser fc = new JFileChooser();
			    int returnVal = fc.showOpenDialog(GCodeTransformerUI.this);
				//
			    if (returnVal == JFileChooser.APPROVE_OPTION) {
			        File file = fc.getSelectedFile();
			        gCodeTransformer.setExportFile(file);			        
			    }
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
