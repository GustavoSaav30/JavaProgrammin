package exercise2;

import javax.swing.JOptionPane;
import java.util.Random;

public class Lotto {
    private int[] numbers;

    public Lotto() {
        numbers = new int[3];
        Random rand = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(9) + 1;
        }
    }

    public int[] getNumbers() {
        return numbers;
    }

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
    }
}
