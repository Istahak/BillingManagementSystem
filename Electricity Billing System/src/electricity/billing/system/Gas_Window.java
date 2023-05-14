package electricity.billing.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Gas_Window extends Utility_Window {
    Gas_Window() {
        super("Gas Window", "images/electricity_window.jpg");
    }
    @Override
    public void Pay() {
        new Select_meter("Pay_Bill", 2);
    }

    @Override
    public void Update() {
        new Update_Bill(2);
    }

    @Override
    public void Generate() {

    }

    @Override
    public void Show_Bill_Details() {
        new Select_meter("Bill_Details", 2);
    }

    @Override
    public void Show_Bill_Info() {
        new Bill_information(2);
    }

    @Override
    public void Cancel() {
        new Bill_window();
    }

    public static void main(String[] args) {
        new Gas_Window();
    }
}