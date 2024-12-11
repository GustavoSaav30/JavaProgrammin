package exercise1;

public class Transaction implements Runnable {
    private final Account account;
    private final double amount;
    private final String type;

    public Transaction(Account account, double amount, String type) {
        this.account = account;
        this.amount = amount;
        this.type = type;
    }

    @Override
    public void run() {
        if ("deposit".equalsIgnoreCase(type)) {
            account.deposit(amount);
        } else if ("withdraw".equalsIgnoreCase(type)) {
            account.withdraw(amount);
        } else {
            System.out.println("Invalid transaction type: " + type);
        }
    }
}