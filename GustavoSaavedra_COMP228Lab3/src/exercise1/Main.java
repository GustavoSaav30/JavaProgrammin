package exercise1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //creating new scanner Object
        Scanner scanner = new Scanner(System.in);
        //asking the user how many insurances will be created
        System.out.println("Enter how many insurances will be create: ");
        int qtd = scanner.nextInt(); //assinging how many will be created
        scanner.nextLine();
        //creating new Insurance array based on the user's input
        Insurance[] insurances = new Insurance[qtd];

        // Loops creating and adding the insurance array
        for (int i = 0; i < insurances.length; i++) {
            //asking user to enter the insurance type
            System.out.println("Enter the type of insurance (Health/Life): ");
            String type = scanner.nextLine().toLowerCase();
            //it will be created the insurance based on the type chosen by the user
            if (type.equals("health")) {
                insurances[i] = new Health();
            } else if (type.equals("life")) {
                insurances[i] = new Life();
            } else {
                System.out.println("Please enter Health or Life.");
                i--; // Ask again
                continue;
            }
            //askiing the monthly cost for the insurance
            System.out.println("Enter the monthly cost for " + type + " insurance: ");
            double cost = scanner.nextDouble();
            scanner.nextLine();
            //setting the insurance cost for the index
            insurances[i].setInsuranceCost(cost);
        }

        System.out.println("\nInsurances Details:");
        //printing the insurance details for each i
        //insurance created
        for (Insurance insurance : insurances) {
            insurance.displayInfo();
            System.out.println();
        }

        scanner.close();
    }
}