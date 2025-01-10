package com.staj.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.staj.components.ButtonClear;
import com.staj.components.ButtonExcel;
import com.staj.components.ButtonSearch;
import com.staj.components.ComboBox;

@SuppressWarnings("serial")
public class PanelSecond extends JPanel{
	
	private int panelWidth = 500;
	private int panelHeight = 700;

	@SuppressWarnings("rawtypes")
	private DefaultListModel model = new DefaultListModel<>();
	@SuppressWarnings("unchecked")
	private JList<String> jList = new JList<String>(model);
	private JScrollPane jScrollPane = new JScrollPane(jList);
	private JTextField searchText = new JTextField();
	
	private String[] labelNames = {"MALZEMEKODU","BIRIM","DESENNO","DENEMENO","EBAT","RENK","IPLIK","TUSE"};
	private String string = "   KOD          "
			+ "BIRIM                   "
			+ "DESENNO                          "	
			+ "DENEMENO                                "
			+ "EBAT                                           "
			+ "RENK                                         "
			+ "IPLIK                                          "
			+ "TUSE";
	private JLabel label = new JLabel(string);
	private ComboBox filtre = new ComboBox(labelNames);
	
	private ButtonSearch buttonS = new ButtonSearch();
	private ButtonExcel buttonE = new ButtonExcel();
	private ButtonClear buttonC = new ButtonClear(this);

	private Color labelColor = new Color(0x588157);
	private Color bgColor = new Color(0xdad7cd);
	private Font fontListe = new Font("Courier New",Font.BOLD,9);
	private Font fontLabel = new Font("Comic Sans",Font.BOLD,12);
	
	public PanelSecond(){
		this.setPreferredSize(new Dimension(panelWidth,panelHeight));
		this.setBackground(bgColor);
		this.setLayout(new GridBagLayout());
		
		label.setOpaque(true);
		label.setBackground(labelColor);
		label.setFont(fontLabel);
		
		GridBagConstraints gbc = new GridBagConstraints(); 
		gbc.anchor = GridBagConstraints.SOUTH;  
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;  
		
		gbc.weighty = 100;
		gbc.gridy = 3;
		gbc.gridwidth = 9;
		gbc.gridx = 0;
		jList.setFont(fontListe);
		this.add(jScrollPane, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.weightx = 10;
		gbc.weighty = 1;
		this.add(searchText, gbc);
		
		gbc.weightx = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 6;
		this.add(buttonS, gbc);

		gbc.gridx = 7;
		this.add(buttonE, gbc);
		
		gbc.gridx = 8;
		this.add(buttonC, gbc);
		
		gbc.weighty = 10;
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = 9;
		this.add(label,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		this.add(filtre, gbc);
	}
	
	@SuppressWarnings("unchecked")
	public DefaultListModel<String> getModel() {
		return model;
	}
	
	public JTextField getSearchText() {
		return searchText;
	}
	
	public String getComboArg() {
		return filtre.getSelectedItem().toString();
	}
}
