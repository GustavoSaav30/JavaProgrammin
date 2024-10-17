package exercise1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many insurances will be create: ");
        int qtd = scanner.nextInt();
        scanner.nextLine();
        Insurance[] insurances = new Insurance[qtd];

        // Loops creating and adding the insurance array
        for (int i = 0; i < insurances.length; i++) {
            System.out.println("Enter the type of insurance (Health/Life): ");
            String type = scanner.nextLine().toLowerCase();

            if (type.equals("health")) {
                insurances[i] = new Health();
            } else if (type.equals("life")) {
                insurances[i] = new Life();
            } else {
                System.out.println("Please enter Health or Life.");
                i--; // Ask again
                continue;
            }

            System.out.println("Enter the monthly cost for " + type + " insurance: ");
            double cost = scanner.nextDouble();
            scanner.nextLine();

            insurances[i].setInsuranceCost(cost);
        }

        System.out.println("\nInsurances Details:");
        for (Insurance insurance : insurances) {
            insurance.displayInfo();
            System.out.println();
        }

        scanner.close();
    }
}

