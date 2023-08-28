import org.postgresql.Driver;

import java.sql.*;

public class Test5 {
    public static void main(String[] args) throws Exception {
        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc","postgres","root");
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from developers");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from developers  where name = ?");
        preparedStatement.setString(1,"Igor");
//        while (resultSet.next()){
//            System.out.println("id " + resultSet.getInt("id"));
//            System.out.println("Name " + resultSet.getString("name"));
//        }
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("id " + resultSet.getInt("id"));
            System.out.println("Name " + resultSet.getString("name"));
        }
    }
}
