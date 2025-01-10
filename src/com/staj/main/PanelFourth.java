	package com.staj.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class PanelFourth extends JPanel{
	
	private int panelWidth = 500;
	private int panelHeight = 700;
	
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JList<String> jl = new JList<String>(model);
	private JScrollPane sp = new JScrollPane(jl);
	private GridBagConstraints gbc = new GridBagConstraints(); 
	
	private File file;
	private Scanner scanner;
	private String str;
	
	private Color listColor = new Color(0xdad7cd);
	private Color bgColor = new Color(0xdad7cd);
	
	
	public PanelFourth(){
		this.setPreferredSize(new Dimension(panelWidth,panelHeight));
		this.setBackground(bgColor);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setLayout(new GridBagLayout());
			
        jl.setBackground(listColor);

        try{
        	file = new File("log.txt");
        	scanner = new Scanner(file);
        	while(scanner.hasNextLine()) {
        		str = scanner.nextLine();
        		model.addElement(str);
        	}
        	scanner.close();
        }catch(FileNotFoundException e){
        	e.printStackTrace();
        }
        
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.fill = GridBagConstraints.BOTH; 
	
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 100;	
		gbc.gridwidth = 3;
		this.add(sp, gbc);	
	}
	
	public void writeToLog(String s) {                
		model.addElement(s);
		try {
			FileWriter fw = new FileWriter("log.txt",true);
			fw.write(s);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
