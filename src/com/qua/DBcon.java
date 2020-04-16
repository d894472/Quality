package com.qua;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.sql.*;

public class DBcon {
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/test?serverTimezone=CST&useUnicode=true&characterEncoding=Big5";
    Connection conn=null;
    //DefaultTableModel dtm=new DefaultTableModel();
    public  void Connection(JTable table,DefaultTableModel dtm)throws IOException{

        try {
            conn= DriverManager.getConnection(url,"root","030200");
            Class.forName(driver);

             if(!conn.isClosed()){
                 System.out.println("DB is ok!");

                 String sql="SELECT * FROM test.quality";
                 Statement statm=conn.createStatement();

                 ResultSet rs=statm.executeQuery(sql);
                 while(rs.next()){
                     String qno=rs.getString("QualityNo");
                     String simd=rs.getString("SimpleDate".toString());
                     String testingd=rs.getString("TestingDate".toString());
                     String location=rs.getString("Location");
                     String lab=rs.getString("LabName");
                     String quantity=rs.getString("Quantity");
                     String month=rs.getString("Month".toString());
                     double culb=rs.getDouble("Cubic");

                     dtm.addRow(new Object[]{qno,simd,testingd,location,lab,quantity,month,culb});



                 }

             }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
