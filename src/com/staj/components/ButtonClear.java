package com.staj.components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.staj.main.*;

@SuppressWarnings("serial")
public class ButtonClear extends JButton implements ActionListener{
	
	private JPanel parentPanel;
	private PanelFirst pFirst;
	private PanelSecond pSecond;
	private PanelThird pThird;
	
	private Color buttonColor = new Color(0x3a5a40);
	private Color textColor = new Color(0xe8ede4);
	
	public ButtonClear(JPanel panel){
		this.setPreferredSize(new Dimension(70,60));
		this.setText("Temizle");
		this.setFocusable(false);
		this.addActionListener(this);
		this.setBackground(buttonColor);
		this.setForeground(textColor);
		this.setVisible(true);
		this.parentPanel = panel;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(parentPanel.getComponentCount() == 20) {			//panelFirst
			pFirst = (PanelFirst) this.getParent();
			for(int i = 0; i < 8; i++) {
				pFirst.getTextField(i).setText("");
			}		
		}else if(parentPanel.getComponentCount() == 7) {  //panelSecond
			pSecond = (PanelSecond) this.getParent();
			pSecond.getModel().clear();	
		}else{											   //panelThird
			pThird = (PanelThird) this.getParent();
			pThird.getTextArea().setText("");
			pThird.getTextField().setText("");
			pThird.getModelDurum().clear();
			pThird.getModelMalzeme().clear();
		}	
	}
}
