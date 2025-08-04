import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLTest {
    public static void main(String args[]){
        String url = "jdbc:mysql://localhost:3306/library_db";
        String username = "root"; // your MySQL username
        String password = "Akhil@1218"; // replace with your password
        try{
            Connection connection=DriverManager.getConnection(url,username,password);
            System.out.println("✅ Connected to MySQL successfully!");
            connection.close();
        }
        catch (SQLException e){
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
        }
    }
}
