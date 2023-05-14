package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class Select_meter extends JFrame implements ActionListener {
    JLabel nameText, addressText;
    TextField unitText;
    Choice meternumCho, monthCho;
    JButton select, cancel;
    String type;
    int info;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);

    Select_meter(String type, int info) {
        super("Select Meter");
        this.type = type;
        this.info = info;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214, 195, 247));
        add(panel);

        JLabel heading = new JLabel("Select meter");
        heading.setBounds(180, 80, 300, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);
        JLabel meternum = new JLabel("Meter Number");
        meternum.setBounds(100, 180, 170, 40);
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
        meternumCho.setBounds(270, 180, 200, 40);
        panel.add(meternumCho);

        JLabel name = new JLabel("Name");
        name.setBounds(100, 260, 170, 40);
        panel.add(name);

        nameText = new JLabel("");
        nameText.setBounds(270, 260 , 170, 40);
        panel.add(nameText);

        JLabel address = new JLabel("Address");
        address.setBounds(100, 320, 170, 40);
        panel.add(address);

        addressText = new JLabel("");
        addressText.setBounds(270, 320, 170, 40);
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

        select = new JButton("Submit");
        select.setBounds(160, 400, 100, 25);
        select.setBackground(Color.black);
        select.setForeground(Color.white);
        select.addActionListener(this);
        select.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panel.add(select);

        cancel = new JButton("Cancel");
        cancel.setBounds(320, 400, 100, 25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/meter.jpg"));
        Image image = imageIcon.getImage().getScaledInstance((int) (width * (0.6)), (int) height, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon1);
        imageLabel.setBounds(700, 200, (int) (width * (0.6)), (int) height);
        add(imageLabel, "East");

        setSize((int) width, (int) height);
        setLocation(300, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == select) {
            try {
                if (type == "Pay_Bill") {
                    new Pay_bill(meternumCho.getSelectedItem(), info);
                } else if (type == "Bill_Details") {
                    new Bill_details(meternumCho.getSelectedItem(), info);
                } else if (type == "Edit_Customer") {
                    new Edit_Customer(meternumCho.getSelectedItem());
                }
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }

        } else {

            if (type == "Edit_Customer") {
                new Customer_class();
            } else {
                if (info == 1)
                    new ElectricityWindow();
                else new Gas_Window();
            }
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Select_meter("", 1);
    }
}