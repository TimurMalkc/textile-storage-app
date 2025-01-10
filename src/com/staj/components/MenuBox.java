package com.staj.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.staj.main.Frame;

@SuppressWarnings("serial")
public class MenuBox extends JMenuBar implements ActionListener{

	private JMenuItem[] itemsSayfa = new JMenuItem[3];
	private JMenuItem[] itemsVeri = new JMenuItem[2];
	private String[] namesSayfa = {"Kayıt Ekranı", "Listeleme", "Toplu Veri Güncelleme"};
	private String[] namesVeri = {"Log", "Görselleştir"};
	private JMenu mSayfalar;
	private JMenu mVeri;
	private Frame frame;
	 
	private Color textColor = new Color(0xe8ede4);
	private Color itemColor = new Color(0xa3b18a);
	
	public MenuBox(Frame frame) {
		this.frame = frame;
		
		mSayfalar = new JMenu();
		mSayfalar.setForeground(textColor);
		mSayfalar.setText("Sayfalar");
		
		mVeri = new JMenu();
		mVeri.setForeground(textColor);
		mVeri.setText("Veriler");
		
		for(int i = 0; i < 3; i++) {
			itemsSayfa[i] = new JMenuItem();
			itemsSayfa[i].setBackground(itemColor);
			itemsSayfa[i].setForeground(textColor);
			itemsSayfa[i].addActionListener(this);
			itemsSayfa[i].setText(namesSayfa[i]);
			mSayfalar.add(itemsSayfa[i]);
		}
		
		for(int i = 0; i < 2; i++) {
			itemsVeri[i] = new JMenuItem();
			itemsVeri[i].setBackground(itemColor);
			itemsVeri[i].setForeground(textColor);
			itemsVeri[i].addActionListener(this);
			itemsVeri[i].setText(namesVeri[i]);
			mVeri.add(itemsVeri[i]);
		}
		
		this.add(mSayfalar);
		this.add(mVeri);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==itemsSayfa[0]) {
			frame.showCard("p1");
		}else if(e.getSource()==itemsSayfa[1]) {
			frame.showCard("p2");
		}else if(e.getSource()==itemsSayfa[2]) {
			frame.showCard("p3");	
		}else if(e.getSource()==itemsVeri[0]) {
			frame.showCard("p4");
		}else if(e.getSource()==itemsVeri[1]) {
			frame.showCard("p5");
		}
	}
}
