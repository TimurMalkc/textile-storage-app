package com.staj.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import com.staj.components.ButtonRefresh;
import com.staj.components.ComboBox;

             
@SuppressWarnings("serial")
public class PanelFifth extends JPanel{
	
	private int panelWidth = 500;
	private int panelHeight = 700;
	
	private GridBagConstraints gbc = new GridBagConstraints(); 
	
	private String[] labelNames = {"BIRIM","EBAT","RENK","IPLIK","TUSE"};
	private ComboBox kategoriler = new ComboBox(labelNames);
	private ButtonRefresh buttonR = new ButtonRefresh();
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	private JFreeChart chart = ChartFactory.createBarChart("Ürün Bar Grafiği", "Kategoriler", "Sayı", dataset, PlotOrientation.VERTICAL, true, true, false);
	private ChartPanel panel;
	private CategoryPlot plot = chart.getCategoryPlot();
	private BarRenderer barRenderer = (BarRenderer) plot.getRenderer();
	
	private Color bgColor = new Color(0xdad7cd);
	private Color itemColor = new Color(0xa3b18a);
	
	public PanelFifth(){
		this.setPreferredSize(new Dimension(panelWidth,panelHeight));
		this.setBackground(bgColor);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setLayout(new GridBagLayout());
		
		gbc.fill = GridBagConstraints.BOTH; 
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weighty = 0.1;
		gbc.weightx = 1;
		this.add(kategoriler, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weighty = 0.1;
		gbc.weightx = 0.3;
		this.add(buttonR, gbc);
		
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;   	
		gbc.gridwidth = 2; 
		
		panel = new ChartPanel(chart);
		barRenderer.setSeriesPaint(0, itemColor);
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		this.add(panel, gbc);
		
	}
	
	public int getComboArg() {
		return kategoriler.getSelectedIndex();
	}
	
	public DefaultCategoryDataset getDataset() {
		return dataset;
	}
}
