package electricity.billing.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/Bill_system";
        String username = "debian-sys-maint";
        String password = "nhrU2IZD7qbnCW5e";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully.");
            // You can use the connection object to interact with the database
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }catch (Exception ex){
            System.out.println("CCCCCFFFFF");
            ex.printStackTrace();
        }
    }
}
