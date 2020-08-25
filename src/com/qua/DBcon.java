package com.qua;



import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class DBcon {
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/test?serverTimezone=CST&useUnicode=true&characterEncoding=Big5";
    Connection conn=null;
    Document document =new Document(PageSize.A4.rotate());
    int ta=0;
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

    public  void WriterPdf()throws IOException{



        try {
            conn= DriverManager.getConnection(url,"root","030200");
            Class.forName(driver);
            try {
                PdfWriter.getInstance(document,new FileOutputStream("Text20200822.pdf"));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            BaseFont bfch=BaseFont.createFont("MHei-Medium", "UniCNS-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font fontsize=new Font(bfch,14,0);
            document.open();
            PdfPTable ptable=new PdfPTable(8);

            ptable.addCell(new Paragraph("編號",fontsize));
            ptable.addCell(new Paragraph("取樣日期",fontsize));
            ptable.addCell(new Paragraph("送驗日期",fontsize));
            ptable.addCell(new Paragraph("部位",fontsize));
            ptable.addCell(new Paragraph("試驗室",fontsize));
            ptable.addCell(new Paragraph("組數",fontsize));
            ptable.addCell(new Paragraph("28天",fontsize));
            ptable.addCell(new Paragraph("M3",fontsize));



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

                     ptable.addCell(qno);
                     ptable.addCell(simd);
                     ptable.addCell(testingd);
                     ptable.addCell(new Paragraph(location,fontsize));
                     ptable.addCell(new Paragraph(lab,fontsize));
                     ptable.addCell(new Paragraph(quantity,fontsize));
                     ptable.addCell(new Paragraph(month,fontsize));
                     ptable.addCell(new Paragraph(Double.toString(culb),fontsize));



                }

            }
            document.add(ptable);
            document.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}
