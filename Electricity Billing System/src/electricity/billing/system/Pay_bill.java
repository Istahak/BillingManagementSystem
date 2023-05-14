package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class Pay_bill extends JFrame implements ActionListener {
    Choice searchmonthcho;
    String meter;
    JButton pay, back;
    String table = "";
    int info;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);
    JLabel meterNumber, heading, meterNumberText, nameText, name, month, unit, unitText, totalBill, totalBillText, status, statusText;

    Pay_bill(String meter, int type) {
        this.info = type;
        if (type == 1) {
            table = "bill";
        } else {
            table = "gas_bill";
        }
        this.meter = meter;
        setSize((int) width, (int) height);
        setLocation(300, 200);
        setLayout(null);

        heading = new JLabel("Pay Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(200, 30, 400, 30);
        add(heading);

        meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(70, 120, 200, 20);
        meterNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(meterNumber);


        meterNumberText = new JLabel("");
        meterNumberText.setBounds(335, 120, 200, 20);
        meterNumberText.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(meterNumberText);

        name = new JLabel("Name");
        name.setBounds(70, 170, 200, 20);
        name.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(name);


        nameText = new JLabel("");
        nameText.setBounds(335, 170, 200, 20);
        nameText.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(nameText);


        month = new JLabel("Month");
        month.setBounds(70, 230, 200, 20);
        month.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(month);

        searchmonthcho = new Choice();
        searchmonthcho.add("January");
        searchmonthcho.add("February");
        searchmonthcho.add("March");
        searchmonthcho.add("April");
        searchmonthcho.add("May");
        searchmonthcho.add("June");
        searchmonthcho.add("July");
        searchmonthcho.add("August");
        searchmonthcho.add("September");
        searchmonthcho.add("October");
        searchmonthcho.add("November");
        searchmonthcho.add("December");
        searchmonthcho.setBounds(335, 230, 150, 20);
        add(searchmonthcho);

        unit = new JLabel("Unit");
        unit.setBounds(70, 290, 200, 20);
        unit.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(unit);


        unitText = new JLabel("");
        unitText.setBounds(335, 290, 200, 20);
        unitText.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(unitText);

        totalBill = new JLabel("Total Bill");
        totalBill.setBounds(70, 350, 200, 20);
        totalBill.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(totalBill);


        totalBillText = new JLabel("");
        totalBillText.setBounds(335, 350, 200, 20);
        totalBillText.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(totalBillText);


        status = new JLabel("Status");
        status.setBounds(70, 410, 200, 20);
        status.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(status);


        statusText = new JLabel("");
        statusText.setBounds(335, 410, 200, 20);
        statusText.setFont(new Font("Tahoma", Font.BOLD, 14));
        statusText.setForeground(Color.RED);
        add(statusText);

        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from C_data where Meter_Number = '" + meter + "'");
            while (resultSet.next()) {
                meterNumberText.setText(meter);
                nameText.setText(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchmonthcho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                database c = new database();
                try {
                    ResultSet resultSet = c.statement.executeQuery("select * from " + table + " where meter_no = '" + meter + "' and month = '" + searchmonthcho.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        unitText.setText(resultSet.getString("unit"));
                        totalBillText.setText(resultSet.getString("total_bill"));
                        statusText.setText(resultSet.getString("status"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.black);
        pay.setForeground(Color.white);
        pay.setBounds(130, 490, 120, 35);
        pay.addActionListener(this);
        pay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(pay);


        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        getContentPane().setBackground(new Color(135, 150, 196));
        back.setBounds(290, 490, 120, 35);
        back.addActionListener(this);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(back);

        setLayout(new BorderLayout());
//        add(panel, "Center");
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/pay.jpg"));
        Image image = imageIcon.getImage().getScaledInstance((int) (width * (0.5)), (int) height, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon1);
        imageLabel.setBounds(700, 200, (int) (width), (int) height);
        add(imageLabel, "East");

        setVisible(true);
    }

    public void Pay() {
        try {
            database c = new database();
            c.statement.executeUpdate("update " + table + " set status = 'Paid' where meter_no = '" + meter + "' and month = '" + searchmonthcho.getSelectedItem() + "'");
        } catch (Exception E) {
            E.printStackTrace();
        }
        JOptionPane.showMessageDialog(pay, "Bill Paid Successfully");
        String arr[] = new String[7];
        if (info == 1) arr[0] = "ELECTRICITY";
        else arr[0] = "GAS";
        arr[1] = nameText.getText();
        arr[2] = meterNumberText.getText();
        arr[3] = searchmonthcho.getSelectedItem();
        arr[4] = unitText.getText();
        arr[5] = totalBillText.getText();
        arr[6] = statusText.getText();
        AA obj = new AA();
        obj.Generate(arr);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pay) {
            Pay();
            if (info == 1) {
                new ElectricityWindow();
            } else {
                new Gas_Window();
            }
            setVisible(false);
        } else {
            if (info == 1)
                new ElectricityWindow();
            else new Gas_Window();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Pay_bill("", 1);
    }
}