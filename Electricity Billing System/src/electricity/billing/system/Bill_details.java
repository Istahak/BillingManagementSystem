package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Bill_details extends JFrame implements ActionListener {
    String meter;
    JButton cancel;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);
    String data_table;
    int type;
    Bill_details(String meter, int type) {
        this.type = type;
        if (type == 1) {
            data_table = "bill";
        } else {
            data_table = "gas_bill";
        }
        this.meter = meter;
        setSize((int) width, (int) height);
        setLocation(300, 200);
        setLayout(null);
        getContentPane().setBackground(new Color(150, 255, 255));

        JTable table = new JTable();

        cancel = new JButton("Cancel");
        cancel.setBounds(1200, 18, 100, 30);
        cancel.setBackground(Color.white);
        cancel.addActionListener(this);
        add(cancel);

        try {
            database c = new database();
            String query_bill = "select * from " + data_table + " where meter_no = '" + meter + "'";
            ResultSet resultSet = c.statement.executeQuery(query_bill);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 60, (int) width, (int) height);
        add(sp);

        setVisible(true);
    }

    public void Back() {
        if(type==1)
        new ElectricityWindow();
        else new Gas_Window();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            try {
                Back();
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Bill_details("", 1);
    }
}