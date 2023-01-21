import java.util.Scanner;

public class appDebug {
    public static void main(String[] args) {
        addUser();
    }
  
    public static void addUser(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("What is your name?");
            String name = scanner.nextLine();
            System.out.println("What is your surname?");
            String surname = scanner.nextLine();
            System.out.println("What is your actual weight?");
            float actualWeight = scanner.nextFloat();
            scanner.nextLine(); // consume the newline character
            System.out.println("What is your height?");
            float height = scanner.nextFloat();
            scanner.nextLine(); // consume the newline character
            System.out.println("What is your weight goal?");
            float goalWeight = scanner.nextFloat();
            scanner.nextLine(); // consume the newline character
            System.out.println("what is your motivation?");
            String motivation = scanner.nextLine(); 
            System.out.println("User data: " + name + " " + surname + " " + actualWeight + " " + height + " " + goalWeight + " " + motivation);
            addToDB(name, surname, actualWeight, height, goalWeight, motivation);
        }
    }
    private static void addToDB(String name, String surname, float actualWeight, float height, float goalWeight, String motivation){
        // implement database connection and insert user data here
    }
}
