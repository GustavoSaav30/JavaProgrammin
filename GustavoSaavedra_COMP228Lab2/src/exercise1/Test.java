package exercise1;

import java.util.Random;

public class Test {
    Random random = new Random();

    public void simulateQuestion() {

    }
    public void checkAnswer() {

    }
    public String generateMessage(boolean isCorrect) {
        int index = random.nextInt(4); // Generate a random number between 0 and 3
        String response = "";

        switch (index) {
            case 0:
                response = isCorrect ? "Excellent!" : "No. Please try again.";
                break;
            case 1:
                response = isCorrect ? "Good!" : "Wrong. Try once more.";
                break;
            case 2:
                response = isCorrect ? "Keep up the good work!" : "Don't give up.";
                break;
            case 3:
                response = isCorrect ? "Nice work!" : "No. Keep trying.";
                break;
        }

        return response;
    }
    public void inputAnswer() {

    }

}
