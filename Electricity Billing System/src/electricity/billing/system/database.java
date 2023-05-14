package electricity.billing.system;

import java.sql.*;

public class database {
    Connection connection;
    Statement statement;
    database(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bill_system","debian-sys-maint","nhrU2IZD7qbnCW5e");
            statement = connection.createStatement();
           // System.out.println("Yes");
        }catch (Exception e){
           // System.out.println("No");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new database();
    }
}