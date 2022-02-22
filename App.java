import java.sql.*;

public class App {
    static final String dbURL = "jdbc:mysql://localhost:3306/cs307group27?useSSL=false";
    static final String dbUser = "Group27";
    static final String dbPassword = "Pcs307g#27";
    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello, World!");
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword))
        {
            System.out.println("Connected!");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
