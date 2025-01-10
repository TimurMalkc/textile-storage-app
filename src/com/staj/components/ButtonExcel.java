package com.staj.components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.staj.main.Check;
import com.staj.main.Frame;
import com.staj.main.PanelSecond;

@SuppressWarnings("serial")
public class ButtonExcel extends JButton implements ActionListener{

	private Frame frame;
	private PanelSecond panel;
	private Check check;
	private String argColumn;
	private String argValue;
	
	private String[] cellNames = {"MALZEMEKODU","BIRIM","DESENNO","DENEMENO","EBAT","RENK","IPLIK","TUSE"};
	private ArrayList<ArrayList<String>> data;
	private Cell[] arrayCell = new Cell[8];
	private Cell tempCell;
	
	private XSSFWorkbook workbook = new XSSFWorkbook();
	private XSSFSheet spreadsheet = workbook.createSheet("Data Sheet");
	private XSSFRow row1 = spreadsheet.createRow(0); 
	 
	private CellStyle styleMembers = workbook.createCellStyle();
	private CellStyle styleHeaders = workbook.createCellStyle();
	private XSSFFont font = workbook.createFont();
	
	private Color buttonColor = new Color(0x3a5a40);
	private Color textColor = new Color(0xe8ede4);
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Calendar cal;
	private String strYes;
	private String strNo;
	
	public ButtonExcel(){	
		this.setPreferredSize(new Dimension(100,50));
		this.setText("Excel'e Aktar");
		this.setFocusable(false);
		this.addActionListener(this);
		this.setBackground(buttonColor);
		this.setForeground(textColor);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		check = new Check();
		panel = (PanelSecond) this.getParent();
		argValue = panel.getSearchText().getText();
		argColumn = panel.getComboArg();
		data = check.returnValue(argColumn,argValue);
		cal = Calendar.getInstance();
		this.frame = (Frame) SwingUtilities.getWindowAncestor(this);
		 
		generateCells();
		
		font.setBold(true);
		styleMembers.setAlignment(HorizontalAlignment.CENTER);
		styleHeaders.setAlignment(HorizontalAlignment.CENTER);
		styleHeaders.setFont(font);
		
		for(int i = 0; i < data.get(0).size(); i++) {
			spreadsheet.createRow(i+1);
			for(int y = 0; y < 8; y++) {
				tempCell = spreadsheet.getRow(i+1).createCell(y);
				tempCell.setCellValue(data.get(y).get(i));
				tempCell.setCellStyle(styleMembers);
			}
		}
		
		for(int i = 0; i < 8; i++) {
			spreadsheet.autoSizeColumn(i);
		}
		
		try(FileOutputStream fileOut = new FileOutputStream("DataWorkbook.xlsx")) {
            workbook.write(fileOut);
            strYes = dateFormat.format(cal.getTime()) + " | " + "Excel dosyası oluşturuldu.\n";
            frame.getPanelFourth().writeToLog(strYes);
			
        }catch (IOException n) {
            n.printStackTrace();
            strNo = dateFormat.format(cal.getTime()) + " | " + "Excel dosyası oluşturulurken bir"
            		+ " hata ile karşılaşıldı.\n";
            frame.getPanelFourth().writeToLog(strNo);
        }
	}
	
	public void generateCells(){
		for(int i = 0; i < 8; i++) {
			arrayCell[i] = row1.createCell(i);
			arrayCell[i].setCellValue(cellNames[i]);
			arrayCell[i].setCellStyle(styleHeaders);
		}
	}	
	
	
}
