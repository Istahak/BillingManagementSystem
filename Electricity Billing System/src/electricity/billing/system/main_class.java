package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class extends JFrame implements ActionListener {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);
    JButton customerButton, billButton, cancelButton, adminButton;

    main_class() {
//        getContentPane().setBackground(Color.white);

        // Create customer button
        customerButton = new JButton("Customer");
        customerButton.setBounds(1100, 150, 200, 80);
        //customerButton.setBackground(Color.BLUE);
        // customerButton.setForeground(Color.WHITE);
        customerButton.setFont(new Font("Arial", Font.PLAIN, 18));
        customerButton.addActionListener(this);
        customerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(customerButton);

        // Create bill button
        billButton = new JButton("Bill");
        billButton.setBounds(1100, 300, 200, 80);
        // billButton.setBackground(Color.GREEN);
        // billButton.setForeground(Color.WHITE);
        billButton.setFont(new Font("Arial", Font.PLAIN, 18));
        billButton.addActionListener(this);
        billButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(billButton);

        // Create cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(1100, 610, 200, 80);
        // cancelButton.setBackground(Color.RED);
        //cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
        cancelButton.addActionListener(this);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(cancelButton);

        // Create admin profile button
        adminButton = new JButton("Admin Profile");
        adminButton.setBounds(1100, 450, 200, 80);
        //adminButton.setBackground(Color.ORANGE);
        //adminButton.setForeground(Color.WHITE);
        adminButton.setFont(new Font("Arial", Font.PLAIN, 18));
        adminButton.addActionListener(this);
        adminButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(adminButton);


        ImageIcon image_icon = new ImageIcon(ClassLoader.getSystemResource("images/bg.png"));
        Image image = image_icon.getImage().getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);
        ImageIcon image_icon2 = new ImageIcon(image);
        JLabel image_label = new JLabel(image_icon2);
        image_label.setBounds(250, 170, (int) width, (int) height);
        add(image_label);
        setSize((int) width, (int) height);
        setLocation(300, 200);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == customerButton) {

            new Customer_class();
            setVisible(false);
        } else if (actionEvent.getSource() == adminButton) {
            new Admin_profile(Login.Admin_name, Login.Admin_id, Login.Admin_username, Login.Admin_password);
            setVisible(false);
        } else if (actionEvent.getSource() == cancelButton) {
            setVisible(false);
        } else if (actionEvent.getSource() == billButton) {

            new Bill_window();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new main_class();
    }
}
