import java.math.BigDecimal;
import java.sql.*;

public class Test {
    // JDBC URL, username and password of PostgreSQL server
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    // Переменные JDBC для открытия и управления соединением
    private static Connection CONNECTION;
    private static Statement STATEMENT;
    private static ResultSet RS;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM developers";
        try {
            // Создание соединения к БД
            CONNECTION = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            // Получение объекта Statement для выполнения запроса
            STATEMENT = CONNECTION.createStatement();
            // Выполнение запроса SELECT
            RS = STATEMENT.executeQuery(query);
            System.out.println("\nDevelopers:");
            while (RS.next()) {
                int id = RS.getInt("id");
                String name = RS.getString("name");
                String specialty = RS.getString("specialty");
                BigDecimal salary = RS.getBigDecimal("salary");
                System.out.println("");
                System.out.println("id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary: $" + salary);
            }
        } catch
        (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            // Закрытие соединения и тд
            try {
                CONNECTION.close();
            } catch
            (SQLException se) {
                System.out.println("Произошла ошибка при закрытии ");
            }
            try {
                STATEMENT.close();
            } catch
            (SQLException se) {
                System.out.println("Произошла ошибка при закрытии ");
            }
            try {
                RS.close();
            } catch
            (SQLException se) {
                System.out.println("Произошла ошибка при закрытии ");
            }
        }
    }
}