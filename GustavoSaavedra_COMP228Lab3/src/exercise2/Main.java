package exercise2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //creating new scanner object

        System.out.println("Enter a name for the game tester: ");
        String name = scanner.nextLine();// scanning the console for the name input

        System.out.println("Choose the type of game tester:");
        System.out.println("1. Full-Time");
        System.out.println("2. Part-Time");
        //scanning for the type input
        int type = scanner.nextInt();
        //initializing the GameTester object
        GameTester tester;
        //depending on the user input choice it will assign tester to the correct game tester obj
        if (type == 1) {
            tester = new FullTimeGameTester(name);
        } else if (type == 2) {
            //if is type 2, partTime the system ask how many hours worked
            System.out.println("Enter the number of hours worked: ");
            int hoursWorked = scanner.nextInt();
            tester = new PartTimeGameTester(name, hoursWorked);
        } else {
            System.out.println("Invalid choice. Exiting.");
            scanner.close();
            return;
        }
        //displaying info
        System.out.println("\nGame Tester Information:");
        tester.displayInfo();

        scanner.close();
    }
}