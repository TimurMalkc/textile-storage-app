package com.staj.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.staj.components.ButtonClear;
import com.staj.components.ButtonExit;
import com.staj.components.ButtonSave;

@SuppressWarnings("serial")
public class PanelThird extends JPanel{
	
	private int panelWidth = 1200;
	private int panelHeight = 700;
	
	private List<String> kategoriler = Arrays.asList("MALZEMEKODU","BIRIM","DESENNO","DENEMENO","EBAT","RENK","IPLIK","TUSE");
	@SuppressWarnings("rawtypes")
	private DefaultListModel[] arrayModel = new DefaultListModel[3];
	@SuppressWarnings("unchecked")
	private JList<String>[] arrayJList = new JList[3];
	private JScrollPane[] arrayJScrollPane = new JScrollPane[3];
	
	private String[] labelNames = {"Güncellenecek Malzeme Alanını Seçiniz","Bilgi","Malzeme","Malzeme Numaraları","Durum"};
	private JLabel[] arrayLabels = new JLabel[5];
	private JTextField bilgi = new JTextField();
	private JTextArea malzemeler = new JTextArea();
	private ButtonSave buttonS = new ButtonSave("UPDATE", this);
	private ButtonClear buttonC = new ButtonClear(this);
	private ButtonExit buttonE = new ButtonExit();

	private Color labelColor = new Color(0x588157);
	private Color listColor = new Color(0xdad7cd);
	private Color bgColor = new Color(0xdad7cd);
	private Font font = new Font("SansSerif",Font.BOLD,15);
	
	@SuppressWarnings("unchecked")
	public PanelThird() {
		this.setPreferredSize(new Dimension(panelWidth,panelHeight));
		this.setBackground(bgColor);
		this.setLayout(new GridBagLayout());
		
		generateModels();
		generateLabels();
		
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.BOTH;  
		
		gbc.insets = new Insets(0,0,2,0);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 0.01;	
		gbc.gridwidth = 1;
		arrayModel[0].addAll(kategoriler);
		arrayJList[0] = new JList<String>(arrayModel[0]);
		arrayJScrollPane[0] = new JScrollPane(arrayJList[0]);
		arrayJList[0].setBackground(listColor);
		this.add(arrayJScrollPane[0], gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(buttonS, gbc);

		gbc.gridx = 2;
		this.add(buttonC, gbc);
		
		gbc.gridx = 3;
		this.add(buttonE, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weighty = 0.001;	
		gbc.gridwidth = 3;
		bilgi.setFont(font);
		this.add(bilgi, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;	
		gbc.gridwidth = 4;
		this.add(arrayLabels[0], gbc);
		
		gbc.gridy = 2;	
		gbc.gridwidth = 1; 
		this.add(arrayLabels[1], gbc);
		
		gbc.insets = new Insets(0,0,0,0);
		
		gbc.gridx = 1;
		gbc.gridy = 3;	
		gbc.gridwidth = 2; 
		this.add(arrayLabels[2], gbc);
		
		gbc.gridx = 0;
		gbc.gridwidth = 1; 
		this.add(arrayLabels[3], gbc);
		
		gbc.gridx = 3;	
		gbc.gridwidth = 2; 
		this.add(arrayLabels[4], gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.weighty = 1000;	
		this.add(arrayJScrollPane[1], gbc);
		
		gbc.gridx = 3;
		gbc.weighty = 0.1;	
		this.add(arrayJScrollPane[2], gbc);
		
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		malzemeler.setBackground(listColor);
	    this.add(malzemeler, gbc);
	}
	
	@SuppressWarnings("unchecked")
	public void generateModels() {
		for(int i = 0; i < 3; i++) {
			arrayModel[i] = new DefaultListModel<>();
			arrayJList[i] = new JList<String>(arrayModel[i]);
			arrayJScrollPane[i] = new JScrollPane(arrayJList[i]);
		}
	}
	
	public void generateLabels() {
		for(int i = 0; i < 5; i++) {
			arrayLabels[i] = new JLabel(labelNames[i], SwingConstants.CENTER);
			arrayLabels[i].setOpaque(true);
			arrayLabels[i].setBackground(labelColor);
			arrayLabels[i].setFont(font);
		}
	}	
	
	public JTextField getTextField() {
		return bilgi;
	} 
	
	public JTextArea getTextArea() {
		return malzemeler;
	}
	
	public JList<String> getJList() {
		return arrayJList[0];
	}
	
	@SuppressWarnings("rawtypes")
	public DefaultListModel getModelMalzeme() {
		return arrayModel[1];
	}
	
	@SuppressWarnings("rawtypes")
	public DefaultListModel getModelDurum() {
		return arrayModel[2];
	}
}
