package electricity.billing.system;

import Interfaces.Edit_CustomerInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Edit_Customer extends JFrame implements ActionListener, Edit_CustomerInterface {
    JLabel nametext;
    JTextField addressText, cityText, stateText, emailText, phoneText;
    String meter;
    JButton update, cancel, delete;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);

    Edit_Customer(String meter) {
        super("Edit Customer");
        this.meter = meter;
        setBounds(300, 200, (int) width, (int) height);
        getContentPane().setBackground(new Color(229, 255, 227));
        setLayout(null);

        JLabel heading = new JLabel("Edit Customer");
        heading.setBounds(180, 80, 300, 20);
        heading.setFont(new Font("serif", Font.BOLD, 20));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(100, 180, 170, 40);
        add(name);

        nametext = new JLabel("");
        nametext.setBounds(270, 180, 170, 40);
        add(nametext);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(100, 260, 170, 40);
        add(meterNo);

        JLabel meterText = new JLabel("");
        meterText.setBounds(270, 260, 170, 40);
        add(meterText);


        JLabel address = new JLabel("Address");
        address.setBounds(100, 320, 170, 40);
        add(address);

        addressText = new JTextField();
        addressText.setBounds(270, 320, 170, 40);
        add(addressText);

//        JLabel city = new JLabel("City");
//        city.setBounds(30,190,100,20);
//        add(city);
//
//        cityText = new JTextField();
//        cityText.setBounds(150,190,200,20);
//        add(cityText);
//
//        JLabel state = new JLabel("State");
//        state.setBounds(30,230,100,20);
//        add(state);
//
//        stateText = new JTextField();
//        stateText.setBounds(150,230,200,20);
//        add(stateText);
//
//        JLabel email = new JLabel("Email");
//        email.setBounds(30,270,100,20);
//        add(email);
//
//        emailText = new JTextField();
//        emailText.setBounds(150,270,200,20);
//        add(emailText);
//
//        JLabel phone = new JLabel("Phone");
//        phone.setBounds(30,310,100,20);
//        add(phone);
//
//        phoneText = new JTextField();
//        phoneText.setBounds(150,310,200,20);
//        add(phoneText);

        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from C_data where Meter_Number = '" + meter + "'");
            if (resultSet.next()) {
                nametext.setText(resultSet.getString("Name"));
                meterText.setText(resultSet.getString("Meter_Number"));
                addressText.setText(resultSet.getString("Address"));
//                cityText.setText(resultSet.getString("city"));
//                stateText.setText(resultSet.getString("state"));
//                emailText.setText(resultSet.getString("email"));
//                phoneText.setText(resultSet.getString("phone_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        update = new JButton("Update");
        update.setBackground(new Color(33, 106, 145));
        update.setForeground(Color.white);
        update.setBounds(120, 530, 120, 25);
        update.addActionListener(this);
        update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(update);

        cancel = new JButton("Cancel");
        cancel.setBackground(new Color(33, 106, 145));
        cancel.setForeground(Color.white);
        cancel.setBounds(270, 530, 120, 25);
        cancel.addActionListener(this);
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(cancel);

        delete = new JButton("Delete");
        delete.setBackground(new Color(33, 106, 145));
        delete.setForeground(Color.white);
        delete.setBounds(190, 590, 120, 25);
        delete.addActionListener(this);
        delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(delete);


        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/bg.png"));
        Image image = imageIcon.getImage().getScaledInstance(400, 410, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imgLabel = new JLabel(imageIcon1);
        imgLabel.setBounds(600, 130, 400, 410);
        add(imgLabel);

        setVisible(true);

    }

    public void Update() {
        String saddress = addressText.getText();
//            String scity = cityText.getText();
//            String sstate = stateText.getText();
//            String semail = emailText.getText();
//            String sphone = phoneText.getText();

        try {
            database c = new database();
//                c.statement.executeUpdate("update C_data set address ='"+saddress+"', city = '"+scity+"', state = '"+sstate+"', email = '"+semail+"', phone_no ='"+sphone+"' where meter_no = '"+meter+"'");
            c.statement.executeUpdate("update C_data set address ='" + saddress + "'" + "where Meter_Number = '" + meter + "'");
            JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
            new Customer_class();
            setVisible(false);
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
    public void Delete(){
        try {
            database c = new database();
//                c.statement.executeUpdate("update C_data set address ='"+saddress+"', city = '"+scity+"', state = '"+sstate+"', email = '"+semail+"', phone_no ='"+sphone+"' where meter_no = '"+meter+"'");
            c.statement.execute("delete from C_data where Meter_Number = '" + meter + "'");
            JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
            new Customer_class();
            setVisible(false);
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
    public void Cancel(){
        new Customer_class();
        setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            Update();
        } else if (e.getSource() == delete) {
            Delete();
        } else {
            Cancel();
        }
    }

    public static void main(String[] args) {
        new Edit_Customer("276");
    }
}