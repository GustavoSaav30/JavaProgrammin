package exercise1;

public class Account {
    private double funds;

    //constructor
    public Account(double initialFunds) {
        if (initialFunds > 0.0) {
            this.funds = initialFunds;
        }
    }

    public synchronized void deposit(double amount) {
        if (amount > 0.0) {
            funds += amount;
            System.out.printf("Deposited: %.2f, New Funds: %.2f%n", amount, funds);
        }
    }

    public synchronized void withdraw(double amount) {
        if (amount > 0.0 && amount <= funds) {
            funds -= amount;
            System.out.printf("Withdrawn: %.2f, Remaining Funds: %.2f%n", amount, funds);
        } else {
            System.out.printf("Withdrawal of %.2f failed. Insufficient funds. Funds: %.2f%n", amount, funds);
        }
    }

    public double getFunds() {
        return funds;
    }
}