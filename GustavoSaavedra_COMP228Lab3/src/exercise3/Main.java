package exercise3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many mortgage will be create: ");
        int qtd = scanner.nextInt();
        scanner.nextLine();
        Mortgage[] mortgages = new Mortgage[qtd];

        System.out.println("Enter the current prime interest rate (in %): ");
        double primeRate = scanner.nextDouble() / 100;

        for (int i = 0; i < mortgages.length; i++) {
            System.out.println("\nEnter details for Mortgage #" + (i + 1));

            System.out.println("Enter the mortgage type (1 = Business, 2 = Personal): ");
            int mortgageType = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter the mortgage number: ");
            String mortgageNumber = scanner.nextLine();
            System.out.println("Enter the customer name: ");
            String customerName = scanner.nextLine();
            System.out.println("Enter the mortgage amount: ");
            double mortgageAmount = scanner.nextDouble();
            System.out.println("Enter the term (1 = Short-term, 3 = Medium-term, 5 = Long-term): ");
            int term = scanner.nextInt();

            if (mortgageType == 1) {
                mortgages[i] = new BusinessMortgage(mortgageNumber, customerName, mortgageAmount, primeRate, term);
            } else if (mortgageType == 2) {
                mortgages[i] = new PersonalMortgage(mortgageNumber, customerName, mortgageAmount, primeRate, term);
            } else {
                System.out.println("Invalid mortgage type. Please restart.");
                i--; // Restart this iteration
            }
        }

        System.out.println("\nMortgage Information:");
        for (Mortgage mortgage : mortgages) {
            System.out.println("----------------------------");
            System.out.println(mortgage.getMortgageInfo());
        }

        scanner.close();
    }
}