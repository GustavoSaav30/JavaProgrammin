package exercise1;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Test {
    Random random = new Random();

    public String[][] generateQuestions() {
        // Create an array of arrays, each sub-array contains the question and its options
        String[][] questionsArray = {
                {
                        "What is the main purpose of the `public static void main(String[] args)` method in Java?",
                        "It is the entry point of any Java program.",
                        "It is used to define a constructor.",
                        "It is used to declare variables globally.",
                        "It is used for handling exceptions."
                },
                {
                        "Which of the following is NOT a feature of Java?",
                        "Platform independence",
                        "Memory management through Garbage Collection",
                        "Multiple inheritance using classes",
                        "Object-oriented programming"
                },
                {
                        "What is encapsulation in Java?",
                        "Encapsulation is the process of hiding implementation details and showing only functionality.",
                        "Encapsulation is the ability to define more than one method with the same name in a class.",
                        "Encapsulation is the feature that allows inheritance between classes.",
                        "Encapsulation is a way to use multiple threads concurrently."
                },
                {
                        "Which of the following best describes a `constructor` in Java?",
                        "A constructor is a special method used to initialize objects.",
                        "A constructor is a static method used to initialize class variables.",
                        "A constructor is used to define logic for exception handling.",
                        "A constructor is used to terminate a program."
                },
                {
                        "What is polymorphism in Java?",
                        "Polymorphism allows one object to take many forms, such as method overriding and overloading.",
                        "Polymorphism allows defining variables of different data types.",
                        "Polymorphism refers to executing multiple threads concurrently.",
                        "Polymorphism is the process of cleaning up memory automatically."
                }
        };// 1,3,1,1,1

        return questionsArray;
    }
    public boolean checkAnswer(Integer questionIndex, Integer userOption) {
        Integer[] correctOptions = {0,2,0,0,0};
        return correctOptions[questionIndex].equals(userOption);
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
        String[][] questions = generateQuestions();
        int correctAnswers = 0;
        int totalQuestions = questions.length;

        for (int i = 0; i < totalQuestions; i++) {
            String question = questions[i][0];
            String[] options = {questions[i][1], questions[i][2], questions[i][3], questions[i][4]};

            // Show question and get input
            String userInput = (String) JOptionPane.showInputDialog(null, question, "Question " + (i + 1),
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            // Check option
            int selectedOption = Arrays.asList(options).indexOf(userInput);
            System.out.println(selectedOption);

            // Check if the answer is correct and display the result
            boolean isCorrect = checkAnswer(i, selectedOption);
            String feedback = generateMessage(isCorrect);
            JOptionPane.showMessageDialog(null, feedback);

            // Update the correct answers count
            if (isCorrect) {
                correctAnswers++;
            }
        }
        // Final result
        JOptionPane.showMessageDialog(null,
                "Final Result: " + correctAnswers + " out of " + totalQuestions + " correct.\nYour score: "
                        + ((correctAnswers / (double) totalQuestions) * 100) + "%");
    }
}
