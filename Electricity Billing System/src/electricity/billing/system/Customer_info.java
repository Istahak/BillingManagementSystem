package electricity.billing.system;

import com.itextpdf.text.pdf.PdfPTable;
import net.proteanit.sql.DbUtils;

import javax.naming.ldap.ExtendedRequest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class Customer_info extends JFrame implements ActionListener {
    Choice searchMeterCho, searchNameCho;
    JTable table;
    JButton search, print, close;
    JTextField meterText, nameText;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);

    Customer_info() {
        super("Customer Info");
        getContentPane().setBackground(new Color(192, 186, 254));
        setBounds(300, 200, (int)width, (int)height);

        setLayout(null);

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(100, 40, 200, 30);
        add(searchMeter);

//        searchMeterCho = new Choice();
//        searchMeterCho.setBounds(180, 20, 150, 20);
//        add(searchMeterCho);
//
//        try {
//            database c = new database();
//            ResultSet resultSet = c.statement.executeQuery("select * from C_data");
//            while (resultSet.next()) {
//                searchMeterCho.add(resultSet.getString("Meter_Number"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        meterText = new JTextField();
        meterText.setBounds(300, 40, 200, 30);
        add(meterText);

        JLabel searchName = new JLabel("Search By Name");
        searchName.setBounds(900, 40, 160, 30);
        add(searchName);

//        searchNameCho = new Choice();
//        searchNameCho.setBounds(520, 20, 150, 20);
//        add(searchNameCho);
//
//        try {
//            database c = new database();
//            ResultSet resultSet = c.statement.executeQuery("select * from C_data");
//            while (resultSet.next()) {
//                searchNameCho.add(resultSet.getString("Name"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        nameText = new JTextField();
        nameText.setBounds(1070, 40, 200, 30);
        add(nameText);

        table = new JTable();
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from C_data");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 150, (int)width,(int)height);
        scrollPane.setBackground(Color.white);
        add(scrollPane);


        search = new JButton("Search");
        search.setBackground(Color.white);
        search.setBounds(130, 100, 100, 30);
        search.addActionListener(this);
        search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(search);

        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(350, 100, 100, 30);
        print.addActionListener(this);
        print.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(print);

        close = new JButton("Close");
        close.setBackground(Color.white);
        close.setBounds(1150, 100, 100, 30);
        close.addActionListener(this);
        close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(close);

        setVisible(true);
    }

    public void Print() {
        try {
            AA obj = new AA();
            PdfPTable tt = obj.JtableToPdfTable(table);
            obj.Generate(tt, "CUSTOMER INFORMATION TABLE");
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String query_search = "select * from C_data where Meter_Number = '" + meterText.getText() + "' and Name = '" + nameText.getText() + "'";
            try {
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                Print();
                JOptionPane.showMessageDialog(null, "User Information Printed Successfully");

            } catch (Exception E) {
                E.printStackTrace();
            }

        } else {
            new Customer_class();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Customer_info();
    }
}