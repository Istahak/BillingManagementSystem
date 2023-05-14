package electricity.billing.system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Customer_class extends JFrame implements ActionListener {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);
    JButton addCustomerButton, editCustomerButton, customerInfoButton, backbutton;

    Customer_class() {
        super("Customer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null); // Using null layout to use setBounds()

        // Create background panel
//        JPanel backgroundPanel = new JPanel();
//        backgroundPanel.setBounds(0, 0, 600, 400);
//        backgroundPanel.setLayout(null);


        // Create add customer button
        addCustomerButton = new JButton("Add Customer");
        addCustomerButton.setBounds(50, 70, 200, 80);
        addCustomerButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addCustomerButton.addActionListener(this);
        addCustomerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(addCustomerButton);

        // Create edit customer button
        editCustomerButton = new JButton("Edit Customer");
        editCustomerButton.setBounds(50, 220, 200, 80);
        editCustomerButton.setFont(new Font("Arial", Font.PLAIN, 18));
        editCustomerButton.addActionListener(this);
        editCustomerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(editCustomerButton);

        // Create customer info button
        customerInfoButton = new JButton("Customer Info");
        customerInfoButton.setBounds(50, 350, 200, 80);
        customerInfoButton.setFont(new Font("Arial", Font.PLAIN, 18));
        customerInfoButton.addActionListener(this);
        customerInfoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(customerInfoButton);

        //Create Back button
        backbutton = new JButton("Back");
        backbutton.setBounds(50, 500, 200, 80);
        backbutton.setFont(new Font("Arial", Font.PLAIN, 18));
        backbutton.addActionListener(this);
        backbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(backbutton);


        // Set background image
//        ImageIcon backgroundImage = new ImageIcon("images/bg.png");
//        JLabel backgroundLabel = new JLabel(backgroundImage);
//        backgroundLabel.setBounds(0, 0, 600, 400);
//        backgroundPanel.add(backgroundLabel);
        ImageIcon image_icon = new ImageIcon(ClassLoader.getSystemResource("images/bg.png"));
        Image image = image_icon.getImage().getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);
        ImageIcon image_icon2 = new ImageIcon(image);
        JLabel image_label = new JLabel(image_icon2);
        image_label.setBounds(0, 0, (int) width, (int) height);
        add(image_label);
        setSize((int) width, (int) height);
        setLocation(300, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCustomerButton) {

            new Addcustomer();
            setVisible(false);
        } else if (e.getSource() == customerInfoButton) {

            new Customer_info();
            setVisible(false);
        } else if (e.getSource() == editCustomerButton) {

            new Select_meter("Edit_Customer", 1);
            setVisible(false);
        } else if (e.getSource() == backbutton) {

            new main_class();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Customer_class();
    }
}
