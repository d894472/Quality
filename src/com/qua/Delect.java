package com.qua;



import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delect {
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/test?serverTimezone=CST&useUnicode=true&characterEncoding=Big5";
    Connection conn=null;

    public  void Del(JTextField deltext){

        try {
            conn= DriverManager.getConnection(url,"root","030200");
            Class.forName(driver);

            if(!conn.isClosed()){
                String sql="delete from test.quality where QualityNo=?";  //String sql="INSERT INTO test.quality(QualityNo,SimpleDate,TestingDate,Location,LabName,Quantity,Month,Week,Cubic) VALUES ('a004','2019-10-05','2019-10-07','二、三區B型集水井CC-1、CC-2頂版與牆身；三區A型集水井CB-9牆身','聯昇','1組','2019-10-12','2019-11-05','7.5')";
                PreparedStatement pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,deltext.getText());



                pstmt.executeUpdate();
                pstmt.close();
                conn.close();


            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
