package exercise1;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {
    public static void main(String[] args) {
        Account myAccount = new Account(2000.0);

        // Collections API
        ArrayList<Transaction> operations = new ArrayList<>();
        operations.add(new Transaction(myAccount, 300, "deposit"));
        operations.add(new Transaction(myAccount, 400, "withdraw"));
        operations.add(new Transaction(myAccount, 500, "deposit"));

        // Multithreading
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (Transaction operation : operations) {
            pool.execute(operation);
        }

        pool.shutdown();
        while (!pool.isTerminated()) {}

        System.out.printf("Final Funds in Account: %.2f%n", myAccount.getFunds());
    }
}