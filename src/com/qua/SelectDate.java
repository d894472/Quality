package com.qua;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

public class SelectDate {
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/test?serverTimezone=CST&useUnicode=true&characterEncoding=Big5";
    Connection conn=null;

    public  void Getda(JTable table,DefaultTableModel dfm,JLabel msglab){

        String sql="select * from test.quality where Month =curdate()";
        try {
            conn=DriverManager.getConnection(url,"root","030200");
            Class.forName(driver);

            Statement stamt= conn.createStatement();
            ResultSet rs=stamt.executeQuery(sql);

            while (rs.next()){
                String qno=rs.getString("QualityNo");
                String simd=rs.getString("SimpleDate".toString());
                String testingd=rs.getString("TestingDate".toString());
                String location=rs.getString("Location");
                String lab=rs.getString("LabName");
                String quantity=rs.getString("Quantity");
                String month=rs.getString("Month".toString());
                double culb=rs.getDouble("Cubic");

                // dfm.getRowCount();

                dfm.addRow(new Object[]{qno,simd,testingd,location,lab,quantity,month,culb});

            }
//            if(rs.next()==false){
//            msglab.setText("查無此資料!!");
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
