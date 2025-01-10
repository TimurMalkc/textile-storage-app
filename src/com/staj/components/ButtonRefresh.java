package com.staj.components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;

import org.jfree.data.category.DefaultCategoryDataset;

import com.staj.main.Check;
import com.staj.main.PanelFifth;

@SuppressWarnings("serial")
public class ButtonRefresh extends JButton implements ActionListener{
	
	private Check c;
	private PanelFifth p;
	
	private Color buttonColor = new Color(0x3a5a40);
	private Color textColor = new Color(0xe8ede4);
	private DefaultCategoryDataset dataset;
	HashMap<String, Integer> hashM;
	ArrayList<ArrayList<String>> data;
	
	public ButtonRefresh(){
		this.setPreferredSize(new Dimension(70,60));
		this.setText("Yenile");
		this.setFocusable(false);
		this.addActionListener(this);
		this.setBackground(buttonColor);
		this.setForeground(textColor);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		c = new Check();
        p = (PanelFifth) this.getParent();
        dataset = p.getDataset();
        
        data = c.returnValue("", "");
        hashM = new HashMap<String, Integer>();
        
        switch(p.getComboArg()) {
        case 0:	//bırım 1
        	drawGraph(1, "BIRIM");
        	break;
        case 1: //ebat 4
        	drawGraph(4, "EBAT");
        	break;
        case 2: //renk 5
        	drawGraph(5, "RENK");
        	break;
        case 3: //iplik 6
        	drawGraph(6, "IPLIK");
        	break;
        case 4: //tuse 7
        	drawGraph(7, "TUSE");
        	break;
        }
        
        p.revalidate();
        p.repaint();
	}	
	
	public void drawGraph(int num, String str) {
		for(String i : data.get(num)) {
			if(i != null && !i.trim().isEmpty()) {
				if(hashM.containsKey(i)) {
					hashM.put(i, hashM.get(i)+1);
				}else {
					hashM.put(i, 1);
				}
			}	
		}
		
		dataset.clear();
		
        for(String s : hashM.keySet()) {
        	dataset.addValue(hashM.get(s), str, s);
        }
        
	}
}
