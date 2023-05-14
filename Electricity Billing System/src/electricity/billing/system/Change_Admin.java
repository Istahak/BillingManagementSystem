package electricity.billing.system;

import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Change_Admin extends JFrame implements ActionListener{
    private JButton apply, back;
    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField nameField;
    private JTextField idField;
    private TextField usernameField;
    private TextField passwordField;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth()*(0.7);
    double height = screenSize.getHeight()*(0.7);
    public Change_Admin(){
        setTitle("Change Admin Information");
        setSize((int)width, (int)height);
        setLocation(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create name label and field
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(150, 150, 100, 30);
        add(nameLabel);

        nameField = new JTextField(Login.Admin_name);
        nameField.setBounds(250, 150, 150, 30);
        nameField.setEditable(false); // make the field read-only
        add(nameField);

        // Create ID label and field
        idLabel = new JLabel("ID Number:");
        idLabel.setBounds(150, 200, 100, 30);
        add(idLabel);

        idField = new JTextField(Login.Admin_id);
        idField.setBounds(250, 200, 150, 30);
        idField.setEditable(false); // make the field read-only
        add(idField);

        // Create username label and field
        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(150, 250, 100, 30);
        add(usernameLabel);

        usernameField = new TextField();
        usernameField.setBounds(250, 250, 150, 30);
        usernameField.setVisible(true); // make the field read-only
        add(usernameField);

        // Create password label and field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 300, 100, 30);
        add(passwordLabel);

        passwordField = new TextField();
        passwordField.setBounds(250, 300, 150, 30);
        passwordField.setVisible(true); // make the field read-only
        add(passwordField);

        // Set layout and background color
        apply = new JButton("Apply");
        apply.setBackground(new Color(66, 127, 219));
        apply.setForeground(Color.black);
        apply.setBounds(150, 470, 100, 25);
        apply.addActionListener(this);
        apply.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(apply);
        back = new JButton("Back");
        back.setBackground(new Color(66, 127, 219));
        back.setForeground(Color.black);
        back.setBounds(300, 470, 100, 25);
        back.addActionListener(this);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(back);

        setLayout(new BorderLayout());
//        add(panel, "Center");
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/admin.png"));
        Image image = imageIcon.getImage().getScaledInstance((int) (width * (0.3)), (int) (height*0.5), Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon1);
        imageLabel.setBounds(600, -50, (int) (width * (0.6)), (int) height);
        add(imageLabel, "East");

        setLayout(null);
        getContentPane().setBackground(new java.awt.Color(255, 255, 255));
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== apply) {
            String susername = usernameField.getText();
            String password = passwordField.getText();
            try{
                Login.Admin_username = susername;
                Login.Admin_password = password;
                JOptionPane.showMessageDialog(apply,"Admin Information Changed");
            }catch (Exception E){
                E.printStackTrace();
            }

        } else if (e.getSource()==back) {

            new Admin_profile(Login.Admin_name, Login.Admin_id, Login.Admin_username, Login.Admin_password);
            setVisible(false);
        }

    }
    public static void main(String[] args) {
        new Change_Admin();
    }
}
