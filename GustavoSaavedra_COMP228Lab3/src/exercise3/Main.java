package exercise3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //instaciantin a new scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many mortgage will be create: ");
        int qtd = scanner.nextInt(); //scanning for the qtd input
        scanner.nextLine();
        Mortgage[] mortgages = new Mortgage[qtd]; //creating the array of mortgage

        System.out.println("Enter the current prime interest rate (in %): ");
        double primeRate = scanner.nextDouble() / 100;
        //scanning for the rate

        //looping through the array
        for (int i = 0; i < mortgages.length; i++) {
            System.out.println("\nEnter details for Mortgage #" + (i + 1));

            System.out.println("Enter the mortgage type (1 = Business, 2 = Personal): ");
            int mortgageType = scanner.nextInt(); //scaning for the type
            scanner.nextLine();

            System.out.println("Enter the mortgage number: ");
            String mortgageNumber = scanner.nextLine(); // scanniing for the number
            System.out.println("Enter the customer name: ");
            String customerName = scanner.nextLine(); // scanning for the customer name
            System.out.println("Enter the mortgage amount: ");
            double mortgageAmount = scanner.nextDouble(); // scanning for the amout
            System.out.println("Enter the term (1 = Short-term, 3 = Medium-term, 5 = Long-term): ");
            int term = scanner.nextInt(); // scanning for the term

            //creating the object based on the user inputs
            if (mortgageType == 1) {
                mortgages[i] = new BusinessMortgage(mortgageNumber, customerName, mortgageAmount, primeRate, term);
            } else if (mortgageType == 2) {
                mortgages[i] = new PersonalMortgage(mortgageNumber, customerName, mortgageAmount, primeRate, term);
            } else {
                System.out.println("Invalid mortgage type. Please restart.");
                i--; // Restart this iteration
            }
        }

        //looping to the array to display info
        System.out.println("\nMortgage Information:");
        for (Mortgage mortgage : mortgages) {
            System.out.println("----------------------------");
            System.out.println(mortgage.getMortgageInfo());
        }

        scanner.close();
    }
}