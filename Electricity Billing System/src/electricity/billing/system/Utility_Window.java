package electricity.billing.system;

import Interfaces.Utility_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Utility_Window extends JFrame implements ActionListener, Utility_interface {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);
    JButton billInfoButton, billDetailsButton, updateBillButton, payBillButton, generateBillButton, cancelButton;
    public Utility_Window(String windowtitle,String imageurl) {

        setTitle(windowtitle);

        billInfoButton = new JButton("Bill Information");
        billInfoButton.setFont(new Font("Arial", Font.PLAIN, 18));
        billInfoButton.setBackground(new Color(100, 170, 255));
        billInfoButton.setForeground(Color.BLACK);
        billInfoButton.setBounds(150, 150, 250, 70);
        billInfoButton.addActionListener(this);
        billInfoButton.setBorderPainted(false);
        billInfoButton.setFocusPainted(false);
        billInfoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(billInfoButton);

        billDetailsButton = new JButton("Bill Details");
        billDetailsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        billDetailsButton.setBackground(new Color(100, 170, 255));
        billDetailsButton.setForeground(Color.BLACK);
        billDetailsButton.setBounds(450, 150, 250, 70);
        billDetailsButton.addActionListener(this);
        billDetailsButton.setBorderPainted(false);
        billDetailsButton.setFocusPainted(false);
        billDetailsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(billDetailsButton);

        updateBillButton = new JButton("Update Bill");
        updateBillButton.setFont(new Font("Arial", Font.PLAIN, 18));
        updateBillButton.setBackground(new Color(100, 170, 255));
        updateBillButton.setForeground(Color.BLACK);
        updateBillButton.setBounds(150, 250, 250, 70);
        updateBillButton.addActionListener(this);
        updateBillButton.setBorderPainted(false);
        updateBillButton.setFocusPainted(false);
        updateBillButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(updateBillButton);

        payBillButton = new JButton("Pay Bill");
        payBillButton.setFont(new Font("Arial", Font.PLAIN, 18));
        payBillButton.setBackground(new Color(100, 170, 255));
        payBillButton.setForeground(Color.BLACK);
        payBillButton.setBounds(450, 250, 250, 70);
        payBillButton.addActionListener(this);
        payBillButton.setBorderPainted(false);
        payBillButton.setFocusPainted(false);
        payBillButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(payBillButton);

        generateBillButton = new JButton("Generate Bill");
        generateBillButton.setFont(new Font("Arial", Font.PLAIN, 18));
        generateBillButton.setBackground(new Color(100, 170, 255));
        generateBillButton.setForeground(Color.BLACK);
        generateBillButton.setBounds(150, 350, 250, 70);
        generateBillButton.addActionListener(this);
        generateBillButton.setBorderPainted(false);
        generateBillButton.setFocusPainted(false);
        generateBillButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(generateBillButton);

        cancelButton = new JButton("Back");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
        cancelButton.setBackground(new Color(100, 170, 255));
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setBounds(450, 350, 250, 70);
        cancelButton.addActionListener(this);
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(cancelButton);

        ImageIcon image_icon = new ImageIcon(ClassLoader.getSystemResource(imageurl));
        Image image = image_icon.getImage().getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);
        ImageIcon image_icon2 = new ImageIcon(image);
        JLabel image_label = new JLabel(image_icon2);
        image_label.setBounds(0, 0, (int) width, (int) height);
        add(image_label);

        setSize((int) width, (int) height);
        setLocation(300, 200);
        setResizable(false);
        setVisible(true);
    }

    public void Pay() {
    }

    public void Update() {
//        new Update_Bill();
    }

    public void Generate() {

    }

    public void Show_Bill_Details() {
//        new Select_meter("Bill_Details");
    }

    public void Show_Bill_Info() {
//        new Bill_information();
    }

    public void Cancel() {
//        new Bill_window();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == payBillButton) {
            try {
                Pay();
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        if (actionEvent.getSource() == updateBillButton) {
            try {
                Update();
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        if (actionEvent.getSource() == generateBillButton) {
            try {
                new Login();
                setVisible(false);

            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        if (actionEvent.getSource() == billDetailsButton) {
            try {
                Show_Bill_Details();
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        if (actionEvent.getSource() == billInfoButton) {
            try {
                Show_Bill_Info();
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        if (actionEvent.getSource() == cancelButton) {
            try {
                Cancel();
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        new Utility_Window();
    }
}
