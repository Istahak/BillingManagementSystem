package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);
    JTextField userText, passwordText;
    Choice loginChoice;
    static String Admin_name = "root";
    static String Admin_password = "123";
    static String Admin_id = "12";
    static String Admin_username = "root";
    JButton loginButton, cancelButton, signupButton;

    Login() {
        super("Login");
        getContentPane().setBackground(Color.white);
        JLabel username = new JLabel("UserName");
        username.setBounds(890, 200, 200, 75);
        add(username);

        userText = new JTextField();
        userText.setBounds(1000, 215, 200, 40);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(890, 265, 200, 75);
        add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(1000, 280, 200, 40);
        add(passwordText);

        JLabel loggin = new JLabel("Loggin In As");
        loggin.setBounds(890, 345, 100, 75);
        add(loggin);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        //loginChoice.add("Customer");
        loginChoice.setBounds(1000, 360, 200, 40);
        add(loginChoice);

        loginButton = new JButton("Login");
        loginButton.setBounds(990, 450, 100, 20);
        loginButton.addActionListener(this);
        add(loginButton);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(1100, 450, 100, 20);
        cancelButton.addActionListener(this);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(cancelButton);

//        signupButton = new JButton("Signup");
//        signupButton.setBounds(400,215,100,20);
//        signupButton.addActionListener(this);
//        add(signupButton);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("images/bg.png"));
        Image profileTow = profileOne.getImage().getScaledInstance((int) (width * 0.60), (int) height, Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTow);
        JLabel profileLable = new JLabel(fprofileOne);
        profileLable.setBounds(0, 0, (int) (width * 0.60), (int) height);
        add(profileLable);


        setSize((int) width, (int) height);
        setLocation(300, 200);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String susername = userText.getText();
            String spassword = passwordText.getText();
            String suser = loginChoice.getSelectedItem();
            if (susername.equals(Admin_username) && spassword.equals(Admin_password)) {
                new main_class();
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(loginButton, "Wrong password");

            }
        }
        if (e.getSource() == cancelButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}