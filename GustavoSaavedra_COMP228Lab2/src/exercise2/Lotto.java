package exercise2;

import javax.swing.JOptionPane;
import java.util.Random;

public class Lotto {
    private int[] numbers;

    public Lotto() {
        // instatiate the array of numbers with 3 number
        numbers = new int[3];
        // random object
        Random rand = new Random();
        // getting a random number from 0 to 9 for each index and assigning it to the number
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(9) + 1;
        }
    }
    //get array of numbers
    public int[] getNumbers() {
        return numbers;
    }
    // method returns the sum of the array
    public int sumNumbers() {
        int sum = 0;
        for (int num : numbers) {
            sum = num + sum;
        }
        return sum;
    }

    public void playGame() {
        JOptionPane.showMessageDialog(null, "Instructions:\n" +
                "1. Choose a number between 3 and 27.\n" +
                "2. The system will roll 3 random numbers between 1 and 9.\n" +
                "3. The sum of the 3 numbers will be calculated.\n" +
                "4. You will have up to 5 attempts to match the sum with your chosen number.\n" +
                "5. If you match the sum within 5 attempts, you win. If not, the computer wins!");
        String input = JOptionPane.showInputDialog("Enter a number between 3 and 27:");
        int chosenNumber = Integer.parseInt(input);

        boolean userVictory = false;

        // Looping five times
        for (int attempt = 1; attempt <= 5; attempt++) {
            //generates new random numbers
            Lotto lotto = new Lotto();
            int lottoSum = lotto.sumNumbers();
            int[] numbers = lotto.getNumbers();

            // Display the random numbers and their sum
            String lottoNumbersString = String.format("Lotto random numbers: %d, %d, %d\nSum: %d", numbers[0], numbers[1], numbers[2], lottoSum);
            JOptionPane.showMessageDialog(null, lottoNumbersString, "Lotto Game", JOptionPane.INFORMATION_MESSAGE);

            // Check if user number of choice is the game sum number
            if (chosenNumber == lottoSum) {
                JOptionPane.showMessageDialog(null, "Congratulations! You won the lotto game!", "Winner chicken dinner", JOptionPane.INFORMATION_MESSAGE);
                userVictory = true;
                break;
            }
        }

        if (!userVictory) {
            JOptionPane.showMessageDialog(null, "Sorry, you lost! The computer won this game.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

