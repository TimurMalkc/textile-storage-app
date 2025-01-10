package com.staj.main;

import java.sql.*;
import java.util.ArrayList;

public class Check {
    
    public ArrayList<ArrayList<String>> returnValue(String argColumn, String argValue) {
    	String url = "jdbc:sqlserver://localhost:1433;databaseName=dbMalzeme;"
    			+ "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
    	Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
    	String[] columnNames = {"MALZEMEKODU","BIRIM","DESENNO","DENEMENO","EBAT","RENK","IPLIK","TUSE"};  	
        String sql;
        String value;
        ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
        
        for (int i = 0; i < 8; i++){
            aList.add(new ArrayList<String>());
        }  

        try{
        	connection = DriverManager.getConnection(url);
        	sql = argValue.isEmpty() ? "SELECT * FROM dbo.tblMMalzeme ORDER BY ID" :
        		"SELECT * FROM dbo.tblMMalzeme WHERE %s = '%s' ORDER BY ID".formatted(argColumn, argValue);
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
            	for(int i = 0; i < 8; i++) {
            		value = resultSet.getString(columnNames[i]);
            		aList.get(i).add(value != null ? value.trim() : null);
            	}
            }
            
            resultSet.close();
            statement.close();
            connection.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        
        return aList;
    }
     
}
