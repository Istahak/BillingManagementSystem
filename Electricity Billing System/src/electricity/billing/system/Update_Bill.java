package electricity.billing.system;

import Interfaces.UpdateBillInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class Update_Bill extends JFrame implements ActionListener, UpdateBillInterface {
    JLabel nameText, addressText;
    TextField unitText;
    Choice meternumCho, monthCho;
    JButton submit, cancel;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);
    String table;

    Update_Bill(int type) {
        if (type == 1) {
            table = "bill";
        } else {
            table = "gas_bill";
        }

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 200, 150));
        add(panel);
        if (type == 1) {
            JLabel heading = new JLabel("Calculate Electricity Bill");
            heading.setBounds(200, 30, 300, 20);
            heading.setFont(new Font("Tahoma", Font.BOLD, 20));
            panel.add(heading);
        } else {
            JLabel heading = new JLabel("Calculate Gas Bill");
            heading.setBounds(200, 30, 300, 20);
            heading.setFont(new Font("Tahoma", Font.BOLD, 20));
            panel.add(heading);
        }

        JLabel meternum = new JLabel("Meter Number");
        meternum.setBounds(130, 100, 150, 30);
        panel.add(meternum);

        meternumCho = new Choice();
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from C_data");
            while (resultSet.next()) {
                meternumCho.add(resultSet.getString("Meter_Number"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        meternumCho.setBounds(300, 100, 150, 30);
        panel.add(meternumCho);

        JLabel name = new JLabel("Name");
        name.setBounds(130, 150, 150, 30);
        panel.add(name);

        nameText = new JLabel("");
        nameText.setBounds(300, 150, 150, 30);
        panel.add(nameText);

        JLabel address = new JLabel("Address");
        address.setBounds(130, 190, 150, 30);
        panel.add(address);

        addressText = new JLabel("");
        addressText.setBounds(300, 190, 150, 30);
        panel.add(addressText);

        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from C_data where Meter_Number = '" + meternumCho.getSelectedItem() + "' ");
            if (resultSet.next()) {
                nameText.setText(resultSet.getString("Name"));
                addressText.setText(resultSet.getString("Address"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        meternumCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from C_data where Meter_Number = '" + meternumCho.getSelectedItem() + "' ");
                    while (resultSet.next()) {
                        nameText.setText(resultSet.getString("Name"));
                        addressText.setText(resultSet.getString("Address"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JLabel unitconsumed = new JLabel("Unit Consumed");
        unitconsumed.setBounds(130, 230, 150, 30);
        panel.add(unitconsumed);

        unitText = new TextField();
        unitText.setBounds(300, 230, 150, 30);
        panel.add(unitText);

        JLabel month = new JLabel("Month");
        month.setBounds(130, 290, 150, 30);
        panel.add(month);

        monthCho = new Choice();
        monthCho.add("January");
        monthCho.add("February");
        monthCho.add("March");
        monthCho.add("April");
        monthCho.add("May");
        monthCho.add("June");
        monthCho.add("July");
        monthCho.add("August");
        monthCho.add("September");
        monthCho.add("October");
        monthCho.add("November");
        monthCho.add("December");
        monthCho.setBounds(300, 290, 150, 30);
        panel.add(monthCho);


        submit = new JButton("Submit");
        submit.setBounds(170, 400, 100, 30);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(300, 400, 100, 30);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/update_information.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(700, (int) height, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon1);
        add(imageLabel, "East");

        setSize((int) width, (int) height);
        setLocation(300, 200);
        setVisible(true);
    }

    public void Submit() {
        String smeterNo = meternumCho.getSelectedItem();
        String sunit = unitText.getText();
        String smonth = monthCho.getSelectedItem();

        int totalBill = 0;
        int units = Integer.parseInt(sunit);
        String query_tax = "select * from tax";
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery(query_tax);
            while (resultSet.next()) {
                totalBill += units * Integer.parseInt(resultSet.getString("cost_per_unit"));
                totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                totalBill += Integer.parseInt(resultSet.getString("service_charge"));
//                    totalBill += Integer.parseInt(resultSet.getString("swacch_bharat"));
                totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));

            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        String query_total_bill = "insert into " + table + " values('" + smeterNo + "', '" + smonth + "','" + sunit + "', '" + totalBill + "','Not Paid')";
        try {
            database c = new database();
            c.statement.executeUpdate(query_total_bill);

            JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
        } catch (Exception E) {
            E.printStackTrace();
        }
        if (table == "bill")
            new ElectricityWindow();
        else new Gas_Window();
        setVisible(false);
    }
    public void Cancel(){
        new ElectricityWindow();
        setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            Submit();

        } else {
            Cancel();
        }
    }

    public static void main(String[] args) {
        new Update_Bill(1);
    }
}