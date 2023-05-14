package electricity.billing.system;

import Interfaces.BillWindowInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Bill_window extends JFrame implements ActionListener, BillWindowInterface {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);
    JButton gasButton, electricityButton, back;

    public Bill_window() {
        ImageIcon gas_icon = new ImageIcon(ClassLoader.getSystemResource("images/gas.png"));
        gasButton = new JButton(gas_icon);
        gasButton.setBounds(800, 200, 225, 225);
        gasButton.addActionListener(this);
        gasButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(gasButton);

        ImageIcon electricity_icon = new ImageIcon(ClassLoader.getSystemResource("images/electricity.png"));
        electricityButton = new JButton(electricity_icon);
        electricityButton.setBounds(300, 200, 225, 225);
        electricityButton.addActionListener(this);
        electricityButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(electricityButton);

        back = new JButton("Back");
        back.setBounds(560, 610, 200, 70);
        back.setFont(new Font("Arial", Font.PLAIN, 40));
        back.addActionListener(this);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(back);

        ImageIcon image_icon = new ImageIcon(ClassLoader.getSystemResource("images/utility_bg.jpg"));
        Image image = image_icon.getImage().getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);
        ImageIcon image_icon2 = new ImageIcon(image);
        JLabel image_label = new JLabel(image_icon2);
        image_label.setBounds(300, 200, (int) width, (int) height);
        add(image_label);

        setSize((int) width, (int) height);
        setLocation(300, 200);
        setResizable(false);
        setVisible(true);
    }

    public void Electricity() {
        new ElectricityWindow();
    }

    public void Gas() {
        new Gas_Window();
    }

    public void Back() {
        new main_class();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == gasButton) {
            new Gas_Window();
            setVisible(false);
        }
        if (actionEvent.getSource() == electricityButton) {
            new ElectricityWindow();
            setVisible(false);
        }
        if (actionEvent.getSource() == back) {
            new main_class();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Bill_window();
    }
}
