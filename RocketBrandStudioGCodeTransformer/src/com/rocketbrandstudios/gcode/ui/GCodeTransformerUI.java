package com.rocketbrandstudios.gcode.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.rocketbrandstudios.gcode.GCodeTransformer;
import com.rocketbrandstudios.gcode.service.transformer.Defaults;
import com.rocketbrandstudios.gcode.service.transformer.transformations.ScaleValuesTransformation;

public final class GCodeTransformerUI extends JPanel {
	final Color COLOR = Color.WHITE;//new Color(Color.RED.getRGB());//0xEFF1F2);
	final Color BUTTON_COLOR = new Color(0X95A0A6);
	/**
	 * 
	 */
	JTextField fFactorField;
	JTextField sFactorField;
	private static final long serialVersionUID = 1L;
	private final GCodeTransformer gCodeTransformer;
	private JTextField fUpperField;
	private JSlider fScaling;
	private JSlider sScaling;
	JTextField fScalingField;
	JTextField sScalingField;
	private File in = new File(Defaults.inFileName);
	private File out = new File(Defaults.outFileName);
	private JLabel inFileLabel;
	private JLabel outFileLabel;

	public GCodeTransformerUI(GCodeTransformer gCodeTransformer){
		super(new BorderLayout());
		this.gCodeTransformer = gCodeTransformer;
		
		setSize(new Dimension(800, 800));
		setBackground(COLOR);
		addToolBar();
		addParametrisation();
		addStatusBar();
	}

	private void addStatusBar() {
		JPanel statusBar = new JPanel(new BorderLayout());
		statusBar.setOpaque(false);
//		statusBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		add(statusBar,BorderLayout.SOUTH);

		JPanel logoPane = new JPanel(new BorderLayout());
		logoPane.setOpaque(false);
		add(logoPane,BorderLayout.SOUTH);
		
		JLabel logo = new JLabel(new ImageIcon(getClass().getResource("RBSLogo.png")));
		logoPane.add(logo,BorderLayout.CENTER);
		logoPane.add(statusBar,BorderLayout.SOUTH);
		
		inFileLabel = new JLabel("In: "+in.getAbsolutePath());
		statusBar.add(inFileLabel,BorderLayout.WEST);
		outFileLabel = new JLabel("Out: "+out.getAbsolutePath());
		statusBar.add(outFileLabel,BorderLayout.EAST);
	}

	private void addParametrisation() {
		JPanel parametrisation = new JPanel(new GridLayout(1,3));
		parametrisation.setOpaque(false);
		add(parametrisation,BorderLayout.CENTER);

		JPanel param = new JPanel(new GridLayout(9,1,10,0));
		param.setOpaque(false);
		JPanel paramContainer = new JPanel(new BorderLayout());
		paramContainer.setOpaque(false);
		paramContainer.add(param, BorderLayout.EAST);
		parametrisation.add(paramContainer);

		param.add(new JLabel("   "));
		
		JPanel p = new JPanel(new GridLayout());
		p.setOpaque(false);
		p.setSize(new Dimension(100, 20));
		param.add(p);
		
		JLabel fLabel = new JLabel("F Factor");
		p.add(fLabel);
		fFactorField = new JTextField(""+Defaults.fFactor, JLabel.SOUTH);
		fFactorField.setHorizontalAlignment(JTextField.RIGHT);
		p.add(fFactorField);
		
		p = new JPanel(new GridLayout());
		p.setOpaque(false);
		param.add(p);
		
		JLabel sLabel = new JLabel("S Factor");
		sLabel.setOpaque(false);
		p.add(sLabel);
		sFactorField = new JTextField(""+Defaults.sFactor, JLabel.SOUTH);
		sFactorField.setHorizontalAlignment(JTextField.RIGHT);
		p.add(sFactorField);
		
		p = new JPanel(new GridLayout());
		p.setOpaque(false);
		param.add(p);
		
		JLabel fUpperLabel = new JLabel("F Upper Limit");
		p.add(fUpperLabel);
		fUpperField = new JTextField(""+Defaults.fUpperLimit, JLabel.SOUTH);
		fUpperField.setHorizontalAlignment(JTextField.RIGHT);
		p.add(fUpperField);
		
		
		p = new JPanel(new GridLayout());
		p.setOpaque(false);
		param.add(p);
		
		JLabel fScalingLabel = new JLabel("F Scaling");
		p.add(fScalingLabel/*, BorderLayout.WEST*/);
		fScalingField = new JTextField(""+Defaults.fScalingDefault, JLabel.SOUTH);
		fScalingField.setHorizontalAlignment(JTextField.RIGHT);
		fScalingField.setEditable(false);
		p.add(fScalingField/*, BorderLayout.CENTER */);
		
		p = new JPanel(new GridLayout());
		p.setOpaque(false);
		param.add(p);
		
		JLabel sScalingLabel = new JLabel("S Scaling");
		p.add(sScalingLabel, BorderLayout.WEST);
		sScalingField = new JTextField(""+Defaults.sScalingDefault, JLabel.SOUTH);
		sScalingField.setHorizontalAlignment(JTextField.RIGHT);
		sScalingField.setEditable(false);
		p.add(sScalingField, BorderLayout.CENTER);
		
		param = new JPanel(new BorderLayout());
		param.setOpaque(false);
		parametrisation.add(param);
		
		fLabel = new JLabel("Adjust F Value", JLabel.CENTER);
		fLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		param.add(fLabel, BorderLayout.NORTH);
		fScaling = new JSlider(JSlider.VERTICAL,1,200,Defaults.fScalingDefault);
		fScaling.setOpaque(false);
		param.add(fScaling, BorderLayout.CENTER);
		fScaling.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				fScalingField.setText(""+fScaling.getValue());
			}
		});
		fScaling.setMajorTickSpacing(10);
		fScaling.setPaintTicks(true);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( 1 ), new JLabel("1%") );
		labelTable.put( new Integer( Defaults.fScalingDefault ), new JLabel(""+Defaults.fScalingDefault+"%") );
		labelTable.put( new Integer( 200 ), new JLabel("200%") );
		fScaling.setLabelTable( labelTable );
		fScaling.setPaintLabels(true);
		
		param = new JPanel(new BorderLayout());
		param.setOpaque(false);
		parametrisation.add(param);
		
		sLabel = new JLabel("Adjust S Value", JLabel.CENTER);
		fLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		param.add(sLabel, BorderLayout.NORTH);
		sScaling = new JSlider(JSlider.VERTICAL,1,200,100);
		sScaling.setOpaque(false);
		param.add(sScaling);
		
		sScaling.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				sScalingField.setText(""+sScaling.getValue());
			}
		});
		sScaling.setMajorTickSpacing(10);
		sScaling.setPaintTicks(true);
		labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( 1 ), new JLabel("1%") );
		labelTable.put( new Integer( Defaults.sScalingDefault ), new JLabel(""+Defaults.sScalingDefault+"%") );
		labelTable.put( new Integer( 200 ), new JLabel("200%") );
		sScaling.setLabelTable( labelTable );
		sScaling.setPaintLabels(true);
		
	}

	private void addToolBar() {
		JPanel tb = new JPanel();
		tb.setLayout(new GridLayout(4,1));
		tb.setOpaque(false);
		add(tb, BorderLayout.WEST);
		
		JButton select = new JButton("Select Input File ...");
		select.setBackground(BUTTON_COLOR);
		tb.add(select);
		select.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser(in.getAbsolutePath());
			    int returnVal = fc.showOpenDialog(GCodeTransformerUI.this);
				//
			    if (returnVal == JFileChooser.APPROVE_OPTION) {
			        in = fc.getSelectedFile();
			        inFileLabel.setText(in.getName());
			    }
			}
		});
		JButton outFile = new JButton("Change Output File ...");
		outFile.setBackground(BUTTON_COLOR);
		tb.add(outFile);
		outFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser(out.getAbsolutePath());
			    int returnVal = fc.showOpenDialog(GCodeTransformerUI.this);
				//
			    if (returnVal == JFileChooser.APPROVE_OPTION) {
			        out = fc.getSelectedFile();
			        outFileLabel.setText(out.getName());
			    }
			}
		});

		JButton go = new JButton("Go!",
				new ImageIcon(getClass().getResource("GoIcon.png")));
		go.setBackground(BUTTON_COLOR);
		tb.add(go);
		go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GCodeTransformerUI.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try{
					gCodeTransformer.
					transform(in.getAbsolutePath()).
					into(out.getAbsolutePath()).
					with(
							new ScaleValuesTransformation("F",asInt(fFactorField.getText()),fScaling.getValue(),asInt(fUpperField.getText())),
							new ScaleValuesTransformation("S",asInt(sFactorField.getText()),sScaling.getValue(),Integer.MAX_VALUE)
							).
							go();
				}catch(Throwable t){
					t.printStackTrace();
				}finally{
					GCodeTransformerUI.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		
	}

	protected int asInt(String text) {
		return Integer.parseInt(text);
	}
}
