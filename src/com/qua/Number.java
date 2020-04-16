package com.qua;

import javax.swing.*;
import java.sql.*;

public class Number {
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/test?serverTimezone=CST&useUnicode=true&characterEncoding=Big5";
    String qqno="";
    Connection conn= null;

    public void GeNU(JTextField numtext){
        try {
            conn= DriverManager.getConnection(url,"root","030200");
            Class.forName(driver);

            String sql1="select *from test.quality order by QualityNo desc limit 0,1";
            Statement staem=conn.createStatement();
            ResultSet rsno=staem.executeQuery(sql1);
            while (rsno.next()) {
                String quano = rsno.getString("QualityNo");
                int a=Integer.parseInt(quano);
                a=a+1;
                qqno= String.valueOf(a);

            }
            System.out.println(qqno);
            numtext.setText(qqno);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
