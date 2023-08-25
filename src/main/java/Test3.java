import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test3 {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/MyDatabase";
        String user = "postgres";
        String password = "postgres";
        DriverManager.registerDriver(new org.postgresql.Driver());
        Connection connection = DriverManager.getConnection
                (url, user, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from sample");
        while (result.next()) {
            System.out.println("--------------");
            System.out.println("id: " + result.getInt("id"));
            System.out.println("name: " + result.getString("name"));
            System.out.println("is_active: " + result.getBoolean("is_active"));
            System.out.println("insert_date: " + result.getDate("insert_date"));
            System.out.println("insert_time: " + result.getTime("insert_time"));
            System.out.println("price: " + result.getBigDecimal("price"));
            System.out.println("unknown: " + result.getObject("unknown"));

        }
    }
}
