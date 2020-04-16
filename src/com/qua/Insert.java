package com.qua;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Insert {
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/test?serverTimezone=CST&useUnicode=true&characterEncoding=Big5";
    Connection conn=null;

    public void Insert(JTextField j1, JTextField j2, JTextField j3, JTextField j4, JTextField j5, JTextField j6, JTextField j7, JTextField j8, JDateChooser jdc,JDateChooser jdc1) {
        try {
            conn=DriverManager.getConnection(url,"root","030200");
            Class.forName(driver);
            if(!conn.isClosed()){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    Calendar caler=Calendar.getInstance();
                    String date=sdf.format(jdc.getDate());
                    j2.setText(date);
                    String date1=sdf.format(jdc1.getDate());
                     j3.setText(date1);

                     Date day=sdf.parse(date);
                        caler.setTime(day);
                        caler.add(Calendar.DAY_OF_YEAR,28);
                        String mday=sdf.format(caler.getTime());
                        j7.setText(mday);

                String sql="INSERT INTO test.quality(QualityNo,SimpleDate,TestingDate,Location,LabName,Quantity,Month,Cubic) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pstm=conn.prepareStatement(sql);

                pstm.setString(1,j1.getText());
                pstm.setString(2,j2.getText());
                pstm.setString(3,j3.getText());
                pstm.setString(4,j4.getText());
                pstm.setString(5,j5.getText());
                pstm.setString(6,j6.getText());
                pstm.setString(7,j7.getText());
                pstm.setString(8, String.valueOf(Double.parseDouble(j8.getText())));

                pstm.executeUpdate();
                pstm.close();
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}