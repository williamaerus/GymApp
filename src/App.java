import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// import java.util.jar.Attributes.Name;
// import java.awt.*;
// import javax.swing.*; 

public class App {
    // colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static void main(String[] args) throws Exception {
        readDataFromDB();
    }
    /* create table: CREATE TABLE name_table(column name datatype, column name datatype);
    insert data in table: INSERT INTO table name(column1_value, column2_value);
*/
    public static void addUser(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(ANSI_BLUE+"What is your name?"+ANSI_RESET);
            String name = scanner.nextLine();
            System.out.println(ANSI_BLUE+"What is your surname?"+ANSI_RESET);
            String surname = scanner.nextLine();
            System.out.println(ANSI_BLUE+"What is your actual weight?"+ANSI_RESET);
            float actualWeight = scanner.nextFloat();
            scanner.nextLine(); // consume the newline character
            System.out.println(ANSI_BLUE+"What is your height?"+ANSI_RESET);
            float height = scanner.nextFloat();
            scanner.nextLine(); // consume the newline character
            System.out.println(ANSI_BLUE+"What is your weight goal?"+ANSI_RESET);
            float goalWeight = scanner.nextFloat();
            scanner.nextLine(); // consume the newline character
            System.out.println(ANSI_BLUE+"what is your motivation?"+ANSI_RESET);
            String motivation = scanner.nextLine(); 
            System.out.println(ANSI_RED+"User data: " + name + " " + surname + " " + actualWeight + " " + height + " " + goalWeight + " " + motivation+ANSI_RESET);
            AddData(name, surname, actualWeight, height, goalWeight, motivation);
        }
    }
    public static void CreateDB() {
        // Connect to the database
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/gym.db")) {
            // Create a statement
            Statement statement = connection.createStatement();
            // Create a table
            statement.execute("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT, surname TEXT, actual_weight REAL, height REAL, goal_weight REAL, motivation TEXT)");
            System.out.println("Table created successfully");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error occurred while creating table: " + e.getMessage());
        }
    }
    public static void AddData(String name, String surname, Float actualWeight, Float height, Float goalWeight, String motivation){
        // Connect to the database
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/gym.db")) {
            // Create a statement
        Statement statement = connection.createStatement();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (name, surname, actual_weight, height, goal_weight, motivation) VALUES (?,?,?,?,?,?)");
        stmt.setString(1, name);
        stmt.setString(2, surname);
        stmt.setFloat(3, actualWeight);
        stmt.setFloat(4, height);
        stmt.setFloat(5, goalWeight);
        stmt.setString(6, motivation);
        stmt.executeUpdate();

        statement.close();
    } catch (SQLException e) {
        System.out.println("Error occurred while uploading your data to the database: " + e.getMessage());
    }
}
public static void readDataFromDB() {
    try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/gym.db")) {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            float actualWeight = resultSet.getFloat("actual_weight");
            float height = resultSet.getFloat("height");
            float goalWeight = resultSet.getFloat("goal_weight");
            String motivation = resultSet.getString("motivation");
            System.out.println("ID: " + id + ", Name: " + name + ", Surname: " + surname + ", Actual Weight: " + actualWeight + ", Height: " + height + ", Goal Weight: " + goalWeight + ", Motivation: " + motivation);
        }
        statement.close();
    } catch (SQLException e) {
        System.out.println("Error occurred while reading data from the database: " + e.getMessage());
    }
}

}

