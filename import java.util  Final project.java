import java.util.ArrayList;
import java.util.Scanner;
import java.time.Year;

public class Tester {

    public static void main(String[] args) {
        ArrayList<User> userRecords = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    for(int i = 0; i < 5 ; i++){
                        userRecords.add(createUserRecord(scanner));
                        System.out.println("-------------------------");
                    }
                    break;
                case 2:
                    for (User user : userRecords) {
                        user.display();
                        System.out.println("----------------------");
                    }
                    break;
                case 3:
                    int id = getId(scanner);
                    User userToDisplay = getUserById(userRecords, id);
                    if (userToDisplay != null) {
                        userToDisplay.display();
                    } else {
                        System.out.println("No user found for the given ID.");
                    }
                    break;
                case 4:
                    userRecords.clear();
                    System.out.println("All user records deleted successfully.");
                    break;
                case 5:
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice != 5);
        scanner.close(); // Closing the scanner to prevent resource leak
    }

    private static User createUserRecord(Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Year of Birth: ");
        int yob = scanner.nextInt();
        System.out.print("Enter Height (in cm): ");
        int height = scanner.nextInt();
        System.out.print("Enter Weight (in kg): ");
        int weight = scanner.nextInt();
        return new User(id, name, yob, height, weight);
    }

    private static int getId(Scanner scanner) {
        System.out.print("Enter ID: ");
        return scanner.nextInt();
    }

    private static User getUserById(ArrayList<User> userRecords, int id) {
        for (User user : userRecords) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    private static void displayMenu() {
        System.out.println("\nUser Management Menu:");
        System.out.println("1. Create a record.");
        System.out.println("2. Show data for all users.");
        System.out.println("3. Show data for a selected user.");
        System.out.println("4. Delete all.");
        System.out.println("5. Exit application.");
    }
}

class User {
    private int id;
    private String name;
    private int yob;
    private int height; // Height in cm
    private int weight; // Weight in kg

    public User(int id, String name, int yob, int height, int weight) {
        this.id = id;
        this.name = name;
        this.yob = yob;
        this.height = height;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yob;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public double calculateBMI() {
        double heightInMeters = height / 100.0; // Convert height to meters
        return weight / (heightInMeters * heightInMeters);
    }

    public int calculateAge() {
        int currentYear = Year.now().getValue();
        return currentYear - yob;
    }

    public void display() {
        System.out.println("\nID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Year of Birth: " + yob);
        System.out.println("Height: " + height + " cm");
        System.out.println("Weight: " + weight + " kg");
        System.out.println("BMI: " + calculateBMI());
        System.out.println("Age: " + calculateAge() + " years");
    }
}
