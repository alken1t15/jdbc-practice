import dto.Cat;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBCExample {
    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Так же создадим отдельный метод, который будет возвращать Connection.
    private static Connection connection;
    private static synchronized Connection getConnection() throws SQLException {
        if (connection == null){
            String url = "jdbc:postgresql://localhost:5432/jdbc";
            String user = "postgres";
            String password = "root";
            return DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    // Ну и избавимся от того, чтобы создавать только статичные методы, создав объект класса JDBCExample и вызвав его публичный не статический метод.
    public static void main(String[] args) throws Exception {
        new JDBCExample().run();
    }

    private void run() throws Exception {
        Cat cat = new Cat(null, "Sky", "Blue");
        insert(cat);
        System.out.println(cat);
        System.out.println("----------");

        System.out.println
                (findById(4));
        System.out.println("----------");
        System.out.println(findAll());

    }

    private Cat findById(int id) throws Exception {
        try (
                Connection connection = getConnection();
                PreparedStatement prepared = connection.prepareStatement("select * from cat where id = ?")
        ) {
            prepared.setInt(1, id);
            try (ResultSet result = prepared.executeQuery()) {
                if (result.next()) {
                    return new Cat(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getString("color"));
                } else {
                    return null;

                }

            }

        }
    }

    private List<Cat> findAll() throws Exception {
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("select * from cat");

        ) {
            List<Cat> list = new ArrayList<>();
            while (result.next()) {
                list.add(new Cat(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("color")));

            }
            return list;

        }
    }

    private void delete(int id) throws Exception {
        try (
                Connection connection = getConnection();
                PreparedStatement prepared = connection.prepareStatement("delete from cat where id = ?")
        ) {
            prepared.setInt(1, id);
            prepared.execute();
        }
    }

    private void update(Cat cat) throws Exception {
        try (
                Connection connection = getConnection();
                PreparedStatement prepared = connection.prepareStatement("update cat set name = ?, color = ? where id = ?")
        ) {
            prepared.setString(1, cat.getName());
            prepared.setString(2, cat.getColor());
            prepared.setInt(3, cat.getId());
            prepared.execute();
        }
    }

    private void insert(Cat cat) throws Exception {
        try(
                Connection connection = getConnection();
                PreparedStatement prepared = connection.prepareStatement("insert into cat(name, color) values (?, ?)")
        ) {
            prepared.setString(1, cat.getName());
            prepared.setString(2, cat.getColor());
            System.out.println(prepared.executeUpdate());

            ResultSet result = prepared.getGeneratedKeys();
            if (result.next()) {
                cat.setId(result.getInt(1));
            }
        }
    }

}