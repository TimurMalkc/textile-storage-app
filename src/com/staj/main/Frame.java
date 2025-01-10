package com.staj.main;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.staj.components.MenuBox;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	
	private PanelFirst p1 = new PanelFirst();
	private PanelSecond p2 = new PanelSecond();
	private PanelThird p3 = new PanelThird();
	private PanelFourth p4 = new PanelFourth();
	private PanelFifth p5 = new PanelFifth();
	private MenuBox menu;
	private CardLayout cardLayout;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Calendar cal;
	private String str;
	private ImageIcon icon;
	
	private int menuColor = 0x344e41; 
	
	public Frame(){
		
		cardLayout = new CardLayout();
		getContentPane().setLayout(cardLayout);
	   
	    getContentPane().add(p1, "p1");
	    getContentPane().add(p2, "p2");
	    getContentPane().add(p3, "p3");
	    getContentPane().add(p4, "p4");
	    getContentPane().add(p5, "p5");
	    
	    menu = new MenuBox(this);
	    menu.setBackground(new Color(menuColor));
	    this.setJMenuBar(menu);
	    
	    
	    icon = new ImageIcon("icon.png");
	    this.setIconImage(icon.getImage());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Malzeme Tanım ve Güncelleme");
		this.setVisible(true);
		this.pack();
		
		this.cal = Calendar.getInstance();
		this.str = dateFormat.format(cal.getTime()) + " | " + "%s giriş yaptı.\n".formatted(System.getProperty("user.name"));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cal = Calendar.getInstance();
				str = dateFormat.format(cal.getTime()) + " | " +
				"%s çıkış yaptı.\n".formatted(System.getProperty("user.name"));
				getPanelFourth().writeToLog(str);
			}
		});
		
		getPanelFourth().writeToLog(str);
		
	}

	public void showCard(String cardName){
        cardLayout.show(this.getContentPane(), cardName);
	}
	
	public PanelFourth getPanelFourth() {
		return this.p4;
	}

}
