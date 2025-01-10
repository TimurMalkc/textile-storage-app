package com.staj.components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import com.staj.main.Frame;


@SuppressWarnings("serial")
public class ButtonExit extends JButton implements ActionListener{

	private Frame frame;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Calendar cal;
	private String str;
	
	private Color buttonColor = new Color(0x3a5a40);
	private Color textColor = new Color(0xe8ede4);
	
	public ButtonExit(){
		this.setPreferredSize(new Dimension(70,60));
		this.setText("Çıkış");
		this.setFocusable(false);
		this.addActionListener(this);
		this.setBackground(buttonColor);
		this.setForeground(textColor);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame = (Frame) SwingUtilities.getWindowAncestor(this);
        cal = Calendar.getInstance();
        str = dateFormat.format(cal.getTime()) + " | " + "%s çıkış yaptı."
        		+ "\n".formatted(System.getProperty("user.name"));
		frame.getPanelFourth().writeToLog(str);
		System.exit(0);
	}	

}
