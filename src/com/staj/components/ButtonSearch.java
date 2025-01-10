package com.staj.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import com.staj.main.Check;
import com.staj.main.PanelSecond;

@SuppressWarnings("serial")
public class ButtonSearch extends JButton implements ActionListener {

    private PanelSecond p;
    private Check c;
    private String argColumn;
    private String argValue;
    private String s;
    private int index;
    private int[] columnWidths = {15, 10, 30, 30, 30, 30, 30, 30};
 
    private Color buttonColor = new Color(0x3a5a40);
	private Color textColor = new Color(0xe8ede4);

    public ButtonSearch() {
        this.setPreferredSize(new Dimension(100, 50));
        this.setText("Ara");
        this.setFocusable(false);
        this.addActionListener(this);
        this.setBackground(buttonColor);
		this.setForeground(textColor);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        c = new Check();
        p = (PanelSecond) this.getParent();
        argValue = p.getSearchText().getText();
        argColumn = p.getComboArg();
        ArrayList<ArrayList<String>> data = c.returnValue(argColumn, argValue);
        index = 0;
        
        p.getModel().clear();

        for(int i = 0; i < data.get(0).size(); i++) {
        	s = "";
            for(int y = 0; y < 8; y++) {
            	String value = data.get(y).get(i);
                if(value != null && !value.isEmpty()) {
                	s += value + " ".repeat(columnWidths[y]-value.length());
                }else{
                	s += "Veri yok" + " ".repeat(columnWidths[y]-8);
             	}
            }
            p.getModel().add(index, s.trim());
            index++;
        }
           
        p.revalidate();
        p.repaint();
    }
}
