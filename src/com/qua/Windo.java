package com.qua;


import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DAY_OF_YEAR;

public class Windo extends JFrame {
    BorderLayout blo = new BorderLayout(3, 3);
    public JFrame f1 = new JFrame();
    JPanel jp;
    JTable table;
    JScrollPane jsc;

    int delno=0;
    int numbe=0;
    String numb;

    JLabel nolab = new JLabel("編號:");
    JLabel simplab = new JLabel("取樣日期:");
    JLabel testedlab = new JLabel("送驗日期:");
    JLabel locatlab = new JLabel("部位:");
    JLabel labname = new JLabel("試驗室:");
    JLabel countlab = new JLabel("組數:");
    JLabel monthlab = new JLabel("28天:");

    JLabel culb = new JLabel("M3");
    JLabel deletelab=new JLabel("刪除資料行:");
    JLabel messlab=new JLabel("訊息");

    JTextField no_text = new JTextField(10);
    JTextField simd_text = new JTextField(5);
    JTextField tested_text = new JTextField(5);
    JTextField loca_text = new JTextField(30);
    JTextField labname_text = new JTextField(15);
    JTextField count_text = new JTextField(10);
    JTextField month_text = new JTextField(5);
    //JTextField week_text=new JTextField(5);
    JTextField culb_text = new JTextField(10);
    JTextField delete_text = new JTextField(10);


    JTextField test=new JTextField(10);
    JTextField test1=new JTextField(10);



    Calendar calen = Calendar.getInstance();
    JDateChooser jdc = new JDateChooser(calen.getTime());
    JDateChooser jdc1 = new JDateChooser(calen.getTime());


    JButton insert = new JButton("新增");
    JButton delete = new JButton("刪除");
    JButton view =new JButton("當天");
    JButton test_btu =new JButton("測試");



    DefaultTableModel dtm = new DefaultTableModel();

    public void Windo() {
        f1.setTitle("品管試驗");
        f1.setSize(550, 350);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        f1.setLayout(blo);

        final String[] hreads = {"編號", "取樣日期", "送驗日期", "部位", "試驗室", "組數", "28天", "M3"};
        Object obj[][] = new Object[][]{};
        JTable table = new JTable(obj, hreads);

        dtm.setColumnIdentifiers(hreads);

        jp = new JPanel();
        jp.add(nolab);
        jp.add(no_text);
        jp.add(simplab);
        jp.add(simd_text);
        jp.add(jdc);
        jp.add(testedlab);
        jp.add(tested_text);
        jp.add(jdc1);
        jp.add(locatlab);
        jp.add(loca_text);
        jp.add(labname);
        jp.add(labname_text);
        jp.add(countlab);
        jp.add(count_text);
        jp.add(monthlab);
        jp.add(month_text);

        jp.add(culb);
        jp.add(culb_text);

        jp.add(view);
        jp.add(messlab);

        no_text.setEditable(false);
        month_text.setEditable(false);

        simd_text.setEditable(false);
        tested_text.setEditable(false);
        jp.add(insert);

        jp.add(deletelab);
        jp.add(delete_text);
        jp.add(delete);

        jp.add(test_btu);
        jp.add(test);


        jsc = new JScrollPane(table);
        table.setModel(dtm);
        f1.add(jp, BorderLayout.CENTER);
        f1.add(jsc, BorderLayout.SOUTH);

        DBcon dbcon = new DBcon();
        try {
            dbcon.Connection(table, dtm);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Number nu = new Number();
        nu.GeNU(no_text);

        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                        String seldate=sdf.format(jdc.getDate());
                        simd_text.setText(seldate);
                        String seldate1=sdf.format(jdc1.getDate());
                        tested_text.setText(seldate1);

                try {
                    Date day=sdf.parse(seldate);
                    calen.setTime(day);
                    calen.add(Calendar.DAY_OF_YEAR,28);
                    String mday=sdf.format(calen.getTime());
                    month_text.setText(mday);


                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

             Insert insert=new Insert();
                insert.Insert(no_text,simd_text,tested_text,loca_text,labname_text,count_text,month_text,culb_text,jdc,jdc1);

                       dtm.addRow(new Object[]{no_text.getText(), simd_text.getText(), tested_text.getText(), loca_text.getText(), labname_text.getText(), count_text.getText(), month_text.getText(), culb_text.getText()});
                        messlab.setText("已新增資料");
                        no_text.setText("");
                        simd_text.setText("");
                        tested_text.setText("");
                        loca_text.setText("");
                        labname_text.setText("");
                        count_text.setText("");
                        month_text.setText("");
                        culb_text.setText("");
                        Number numbr=new Number();
                        numbr.GeNU(no_text);
                       numb=no_text.getText();
//                        numb=String.valueOf(numbe);
                        no_text.setText(numb);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectrow=table.getValueAt(table.getSelectedRow(),0).toString();
                int deleterow=table.getSelectedRow();

                delete_text.setText(selectrow);
               Delect dele=new Delect();
               dele.Del(delete_text);
                dtm.removeRow(deleterow);
                messlab.setText("已刪除資料");
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectDate seld=new SelectDate();
                seld.Getda(table,dtm,messlab);

            }
        });
        test_btu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int sel=table.getSelectedRow();
                    int c=table.getSelectedColumn();
                   // test.setText(String.valueOf(c));
                System.out.println("Row:"+sel+","+"Column:"+c);
                String te= String.valueOf(table.getValueAt(sel,c));
                System.out.println(te);
            }
        });

    }

}


