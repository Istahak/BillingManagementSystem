package electricity.billing.system;

import com.itextpdf.text.pdf.PdfPTable;
import com.mysql.cj.xdevapi.Table;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Bill_information extends JFrame implements ActionListener {
    static Choice searchMeterCho, searchMonthCho;
    static JTable table;
    static JTextField meterText, nameText;
    static JButton search, print, close;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);
    int type;
    String data_table;

    Bill_information(int type) {
        super("Bill Information");
        this.type = type;
        if (type == 1) {
            data_table = "bill";
        } else {
            data_table = "gas_bill";
        }
        getContentPane().setBackground(new Color(215, 200, 255));
        setSize((int) width, (int) height);
        setLocation(300, 200);
        setLayout(null);
        JLabel searchMeter = new JLabel("Search By Meter Number: ");
        searchMeter.setBounds(100, 40, 200, 30);
        add(searchMeter);
        meterText = new JTextField();
        meterText.setBounds(300, 40, 200, 20);
        add(meterText);
//        searchMeterCho = new Choice();
//        searchMeterCho.setBounds(300, 40, 200, 30);
//        add(searchMeterCho);
//
//        try {
//            database c = new database();
//            ResultSet resultSet = c.statement.executeQuery("select * from " + data_table);
//            while (resultSet.next()) {
//                searchMeterCho.add(resultSet.getString("meter_no"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        JLabel searchMonth = new JLabel("Search By Month:");
        searchMonth.setBounds(900, 40, 160, 30);
        add(searchMonth);

        searchMonthCho = new Choice();
        searchMonthCho.add("January");
        searchMonthCho.add("February");
        searchMonthCho.add("March");
        searchMonthCho.add("April");
        searchMonthCho.add("May");
        searchMonthCho.add("June");
        searchMonthCho.add("July");
        searchMonthCho.add("August");
        searchMonthCho.add("September");
        searchMonthCho.add("October");
        searchMonthCho.add("November");
        searchMonthCho.add("December");
        searchMonthCho.setBounds(1070, 40, 200, 30);
        add(searchMonthCho);


        table = new JTable();
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from " + data_table);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 150, (int) width, (int) height);
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

    public void Search() {
        String query_search = "select * from " + data_table + " where meter_no = '" + meterText.getText() + "' and month = '" + searchMonthCho.getSelectedItem() + "'";
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery(query_search);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void Print() {
        try {
            AA obj = new AA();
            PdfPTable tt=obj.JtableToPdfTable(table);
            obj.Generate(tt,"BILL INFORMATION TABLE");
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void Back() {
        if (type == 1)
            new ElectricityWindow();
        else new Gas_Window();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            Search();
        } else if (e.getSource() == print) {
            Print();
        } else {
            Back();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Bill_information(1);
    }
}