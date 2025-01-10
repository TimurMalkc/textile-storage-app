package com.staj.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.staj.components.ButtonClear;
import com.staj.components.ButtonExit;
import com.staj.components.ButtonSave;

@SuppressWarnings("serial")
public class PanelFirst extends JPanel{
	
	private int panelWidth = 500;
	private int panelHeight = 700;
	
	private ButtonSave buttonS = new ButtonSave("SAVE", this);
	private ButtonClear buttonC = new ButtonClear(this);
	private ButtonExit buttonE = new ButtonExit();
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JList<String> jl = new JList<String>(model);
	private JScrollPane sp = new JScrollPane(jl);
	private JTextField[] arrayText = new JTextField[8];
	private GridBagConstraints gbc = new GridBagConstraints(); 
	private String[] labelNames = {"MALZEMEKODU","BIRIM","DESENNO","DENEMENO",
									"EBAT","RENK","IPLIK","TUSE"};
	private JLabel[] labels = new JLabel[8];
	
	private Check check;
	private ArrayList<ArrayList<String>> data;
	
	private Color labelColor = new Color(0x588157);
	private Color listColor = new Color(0xdad7cd);
	private Color bgColor = new Color(0xdad7cd);
	private Font font = new Font("SansSerif",Font.BOLD,15);
	
	public PanelFirst(){
		this.setPreferredSize(new Dimension(panelWidth,panelHeight));
		this.setBackground(bgColor);
		this.setLayout(new GridBagLayout());
		
		generateLabels();
		generateTexts();
		
		check = new Check();

        jl.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					data = check.returnValue("","");
					int i = jl.getSelectedIndex();
					for(int y = 0; y < 8; y++) {
						arrayText[y].setText(data.get(y).get(i));
					}
				}
			}
		});
        
        jl.setBackground(listColor);
   
        updatePane();
		
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.gridy = 0;
		
		gbc.gridx = 0;
		this.add(buttonS, gbc);
		gbc.gridx = 1;	
		this.add(buttonC, gbc);
		gbc.gridx = 2;
		this.add(buttonE, gbc);
	
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 100;	
		gbc.gridwidth = 3;
		this.add(sp, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weighty = 10;
		for(int i = 0; i < 8; i++) {
			gbc.gridy = i + 2;
			this.add(arrayText[i], gbc);
		}
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		for(int i = 0; i < 8; i++) {
			gbc.gridy = i+2;
			this.add(labels[i], gbc);
		}
	}

	public void updatePane() {
		model.clear();
		data = check.returnValue("","");
        model.addAll(data.get(0));

	}
	
	public DefaultListModel<String> getModel(){
		return model;
	}
	
	public JTextField getTextField(int i) {
		return arrayText[i];
	}
	
	public void generateLabels() {
		for(int i = 0; i < 8; i++) {
			labels[i] = new JLabel(labelNames[i], SwingConstants.CENTER);
			labels[i].setOpaque(true);
			labels[i].setBackground(labelColor);
			labels[i].setFont(font);
			
		}
	}
	
	public void generateTexts() {
		for(int i = 0; i < 8; i++) {
			arrayText[i] = new JTextField();
			arrayText[i].setFont(font);
		}
	}
}
