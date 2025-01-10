package com.staj.components;

import java.awt.Color;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;

@SuppressWarnings({ "serial", "rawtypes" })
public class ComboBox extends JComboBox{
	
	private DefaultListCellRenderer listRenderer;
	private Color buttonColor = new Color(0x3a5a40);
	private Color textColor = new Color(0xe8ede4);
	
	@SuppressWarnings("unchecked")
	public ComboBox(String[] args) {
		super(args);
		this.listRenderer = new DefaultListCellRenderer();
	    this.listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); 
	    this.setRenderer(listRenderer);
		this.setFocusable(false);
		this.setBackground(buttonColor);
		this.setForeground(textColor);
	}
}
