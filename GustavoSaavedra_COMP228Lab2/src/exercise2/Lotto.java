package exercise2;

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
}
