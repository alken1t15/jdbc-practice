import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test2 {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/MyDatabase";
        String user = "postgres";
        String password = "postgres";
        DriverManager.registerDriver(new org.postgresql.Driver());
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select 1 + 12");
        while (result.next()) {
            result.getInt(1);
        }
    }
}
