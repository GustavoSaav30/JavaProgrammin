package exercise2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a name for the game tester: ");
        String name = scanner.nextLine();

        System.out.println("Choose the type of game tester:");
        System.out.println("1. Full-Time");
        System.out.println("2. Part-Time");

        int type = scanner.nextInt();
        GameTester tester;

        if (type == 1) {
            tester = new FullTimeGameTester(name);
        } else if (type == 2) {
            System.out.println("Enter the number of hours worked: ");
            int hoursWorked = scanner.nextInt();
            tester = new PartTimeGameTester(name, hoursWorked);
        } else {
            System.out.println("Invalid choice. Exiting.");
            scanner.close();
            return;
        }

        System.out.println("\nGame Tester Information:");
        tester.displayInfo();

        scanner.close();
    }
}