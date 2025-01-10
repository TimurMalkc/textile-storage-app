package com.staj.components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.staj.main.Check;
import com.staj.main.Frame;
import com.staj.main.PanelFirst;
import com.staj.main.PanelThird;


@SuppressWarnings("serial")
public class ButtonSave extends JButton implements ActionListener{

	private Frame frame;
	private PanelFirst pFirst;
	private PanelThird pThird;
    private Check c = new Check();
    private String myKod;
    private String myBirim;
    private String myDesenno;
    private String myDenemeno;
    private String myEbat;
    private String myRenk;
    private String myIplik;
    private String myTuse;
    private String myState;
    private java.sql.Date myTarih = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    private String myColumn;
    private String myValue;
    private String myOutput;
    private JPanel parentPanel;
    
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Calendar cal;
	private String strLog;
	private String strFail;
    
    private Color buttonColor = new Color(0x3a5a40);
	private Color textColor = new Color(0xe8ede4);
    
	public ButtonSave(String s, JPanel panel){
		this.setPreferredSize(new Dimension(70,60));
		this.setText("Kaydet");
		this.setFocusable(false);
		this.addActionListener(this);
		this.setBackground(buttonColor);
		this.setForeground(textColor);
		this.setVisible(true);
		this.myState = s;
		this.parentPanel = panel;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void actionPerformed(ActionEvent e){
		this.frame = (Frame) SwingUtilities.getWindowAncestor(this);
		if(parentPanel.getComponentCount() == 20) {		//panelFirst
			pFirst = (PanelFirst) this.getParent(); 
	        myKod = pFirst.getTextField(0).getText();
	        myBirim = pFirst.getTextField(1).getText();
	        myDesenno = pFirst.getTextField(2).getText();
	        myDenemeno = pFirst.getTextField(3).getText();
	        myEbat = pFirst.getTextField(4).getText();
	        myRenk = pFirst.getTextField(5).getText();
	        myIplik = pFirst.getTextField(6).getText();
	        myTuse = pFirst.getTextField(7).getText();
	        
	        try{
	            chooseProcedure(myState, myKod, myBirim, myDesenno, myDenemeno, myEbat, myRenk, myIplik, myTuse, myTarih, "","");
	        }catch (SQLException m){
	            m.printStackTrace();
	        }
	        
	        pFirst.updatePane();
	       
		}else {
			pThird = (PanelThird) this.getParent();
			pThird.getModelDurum().clear();
			pThird.getModelMalzeme().clear();
			
			if(pThird.getTextArea().getText().isEmpty()) 
				return;
		
			myColumn = pThird.getJList().getSelectedValue();
	        myValue = pThird.getTextField().getText();
			
			for(String line : pThird.getTextArea().getText().split("\\n")) {
				try{
					chooseProcedure("UPDATE", line, "", "", "", "", "", "", "", myTarih, myColumn, myValue);
					pThird.getModelMalzeme().add(pThird.getModelMalzeme().size(), line);
		        }catch (SQLException m){
		            m.printStackTrace();
		        }
				
			}
			
		}
        
	}
	
	
	public void chooseProcedure(String myState, String myKod, String myBirim, String myDesenno,
            String myDenemeno, String myEbat, String myRenk, String myIplik,
            String myTuse, java.sql.Date myTarih, String myColumn, String myValue) throws SQLException{
		
			ArrayList<ArrayList<String>> data = c.returnValue("","");
		
			if(parentPanel.getComponentCount() < 20) {  //thirdPanel
				callProcedure(myState, myKod, myBirim, myDesenno, myDenemeno, myEbat,
						myRenk, myIplik, myTuse, myTarih, myColumn, myValue);
				return;
			}
			
			if(myKod.isEmpty() || myBirim.isEmpty() || myDesenno.isEmpty() || myDenemeno.isEmpty() || myEbat.isEmpty() || myRenk.isEmpty() || myIplik.isEmpty() || myTuse.isEmpty()){
				JOptionPane.showMessageDialog(null, "Girilmeyen bilgi bulunuyor", "Uyarı",JOptionPane.INFORMATION_MESSAGE); 
				return;
			}
			
			if(myState.equals("SAVE")){
				if(data.get(0).contains(myKod)){
					int choice = JOptionPane.showConfirmDialog(null, "Malzeme daha önce kaydedilmiş."
							+ " Girilen bilgilerle güncellemek istiyor musunuz?", 
                           "Güncelleme Uyarısı", JOptionPane.YES_NO_CANCEL_OPTION); 
					if(choice == JOptionPane.YES_OPTION){
						try{
				            chooseProcedure("SOLOUPDATE", myKod, myBirim, myDesenno, myDenemeno, myEbat,
				            		myRenk, myIplik, myTuse, myTarih, "","");
				        }catch(SQLException m){
				            m.printStackTrace();
				        }
					}
				}else{
					callProcedure(myState, myKod, myBirim, myDesenno, myDenemeno, myEbat,
							myRenk, myIplik, myTuse, myTarih, "", "");
				}
			}else{
				callProcedure("SOLOUPDATE", myKod, myBirim, myDesenno, myDenemeno, myEbat,
						myRenk, myIplik, myTuse, myTarih, "","");
			}
		}	
	
	
	@SuppressWarnings("unchecked")
	public void callProcedure(String myState, String myKod, String myBirim, String myDesenno,
            String myDenemeno, String myEbat, String myRenk, String myIplik,
            String myTuse, java.sql.Date myTarih, String myColumn, String myValue) throws SQLException{
		
		Connection connection = null;
		CallableStatement statement = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=dbMalzeme;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
		
		try{
			connection = DriverManager.getConnection(url);
			String sql = "{CALL MYPRO(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			statement = connection.prepareCall(sql);
			
			statement.setString(1, myState);
			statement.setString(2, myKod);
			statement.setString(3, myBirim);
			statement.setString(4, myDesenno);
			statement.setString(5, myDenemeno);
			statement.setString(6, myEbat);
			statement.setString(7, myRenk);
			statement.setString(8, myIplik);
			statement.setString(9, myTuse);
			statement.setDate(10, myTarih);
			statement.setString(11, myColumn);
			statement.setString(12, myValue);
			statement.registerOutParameter(13, Types.VARCHAR);
			statement.execute();
			myOutput = statement.getString(13);
			
			
			if(parentPanel.getComponentCount() < 20) 
				pThird.getModelDurum().add(pThird.getModelDurum().size(), myOutput+"\n");
			
			cal = Calendar.getInstance();
			
			strLog = dateFormat.format(cal.getTime()) + " | " + myOutput+"\n";
			frame.getPanelFourth().writeToLog(strLog);
			
			statement.close();
			connection.close();
			
		}catch (SQLException e){
            e.printStackTrace(); 
            strFail = dateFormat.format(Calendar.getInstance().getTime()) + " | " + myOutput + e.getMessage()+"\n";
            frame.getPanelFourth().writeToLog(strFail);
		}
	}
}
