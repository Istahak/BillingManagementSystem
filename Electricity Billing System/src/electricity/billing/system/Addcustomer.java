package electricity.billing.system;

import Interfaces.AddcustomerInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.ActionListener;


public class Addcustomer extends JFrame implements ActionListener, AddcustomerInterface {
    Choice loginASCho;
    static TextField meterText, userNameText, nameText, AddressText;
    static JButton create, back;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);


    Addcustomer() {
        super("Add Cutomer");
        getContentPane().setBackground(new Color(168, 203, 255));

        JLabel createAs = new JLabel("Create Account");
        createAs.setBounds(150, 170, 100, 30);
        add(createAs);

        loginASCho = new Choice();
        loginASCho.add("Customer");
        loginASCho.setBounds(300, 170, 200, 30);
        add(loginASCho);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(150, 270, 100, 30);
        meterNo.setVisible(true);
        add(meterNo);

//        meterText = new TextField();
//        meterText.setBounds(300, 270, 200, 30);
//        meterText.setVisible(false);
//        add(meterText);


        meterText = new TextField();
        meterText.setBounds(300, 270, 200, 30);
        meterText.setVisible(true);
        add(meterText);

        JLabel userName = new JLabel("UserName");
        userName.setBounds(150, 370, 100, 30);
        add(userName);

        userNameText = new TextField();
        userNameText.setBounds(300, 370, 200, 30);
        add(userNameText);


        JLabel name = new JLabel("Name");
        name.setBounds(150, 470, 100, 30);
        add(name);

        nameText = new TextField("");
        nameText.setBounds(300, 470, 200, 30);
        add(nameText);
        JLabel Address = new JLabel("Address");
        Address.setBounds(150, 570, 100, 30);
        add(Address);

        AddressText = new TextField();
        AddressText.setBounds(300, 570, 200, 30);
        add(AddressText);

        create = new JButton("Create");
        create.setBackground(new Color(66, 127, 219));
        create.setForeground(Color.black);
        create.setBounds(200, 670, 100, 25);
        create.addActionListener(this);
        create.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(create);

        back = new JButton("Back");
        back.setBackground(new Color(66, 127, 219));
        back.setForeground(Color.black);
        back.setBounds(400, 670, 100, 25);
        back.addActionListener(this);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(back);
        ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("images/bg.png"));
        Image boyImg = boyIcon.getImage().getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);
        ImageIcon boyIcon2 = new ImageIcon(boyImg);
        JLabel boyLabel = new JLabel(boyIcon2);
        boyLabel.setBounds(600, 0, (int) width, (int) height);
        add(boyLabel);
//
//
        setSize((int) width, (int) height);
        setLocation(300, 200);
        setLayout(null);
        setVisible(true);
    }

    public static void Create() {
//      String sloginAs = loginASCho.getSelectedItem();
        String smeter = meterText.getText();
        String susername = userNameText.getText();
        String sname = nameText.getText();
        String saddress = AddressText.getText();
        try {
            database c = new database();
            String query = null;
//                if (loginASCho.equals("Admin")) {
            query = "insert into C_data value('" + smeter + "', '" + susername + "', '" + sname + "','" + saddress + "')";
//                }else {
//                    query = "update Signup set username = '"+susername+"', password = '"+spassword+"', usertype = '"+sloginAs+"' where meter_no = '"+smeter+"'";
//                }
            c.statement.executeUpdate(query);

            JOptionPane.showMessageDialog(create, "Account Created");
            new Customer_class();

        } catch (Exception E) {
            E.printStackTrace();
        }
    }
    public void CreateCustomer(){
        Addcustomer.Create();
        setVisible(false);
    }
    public void Back(){
        new Customer_class();
        setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            CreateCustomer();
        } else if (e.getSource() == back) {
            Back();
        }

    }

    public static void main(String[] args) {
        new Addcustomer();
    }
}