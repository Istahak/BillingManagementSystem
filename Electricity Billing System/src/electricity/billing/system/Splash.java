package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() * (0.7);
    double height = screenSize.getHeight() * (0.7);
    Splash(){
        ImageIcon imageicon = new ImageIcon(ClassLoader.getSystemResource("images/bg.png"));
        Image imageone = imageicon.getImage().getScaledInstance((int)width, (int)height, Image.SCALE_DEFAULT);
        ImageIcon im2 = new ImageIcon(imageone);
        JLabel imagelabel = new JLabel(im2);
        add(imagelabel);
        setSize((int) width, (int) height);
        setLocation(300, 200);
        setVisible(true);
        try {
            Thread.sleep(5000);
            new Login();
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    public static void main(String[] args){
        new Splash();
    }
}
