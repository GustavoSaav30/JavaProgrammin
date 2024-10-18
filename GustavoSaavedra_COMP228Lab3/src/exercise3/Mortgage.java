package exercise3;

public abstract class Mortgage implements MortgageConstants {
    private String mortgageNumber;
    private String customerName;
    private double mortgageAmount;
    private double interestRate;
    private int term;

    public Mortgage(String mortgageNumber, String customerName, double mortgageAmount, double interestRate, int term) {
        this.mortgageNumber = mortgageNumber;
        this.customerName = customerName;
        if (mortgageAmount > MAX_MORTGAGE) {
            throw new IllegalArgumentException("Mortgage amount cannot exceed $" + MAX_MORTGAGE);
        }
        this.mortgageAmount = mortgageAmount;
        this.interestRate = interestRate;

        if (term != SHORT && term != MEDIUM && term != LONG) {
            this.term = SHORT; // forcing short term
        } else {
            this.term = term;
        }
    }

    public double getMortgageAmount() {
        return mortgageAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getTerm() {
        return term;
    }

    public double calculateTotalAmountOwed() {
        return mortgageAmount + (mortgageAmount * interestRate * term);
    }

    public String getMortgageInfo() {
        return "Mortgage Number: " + mortgageNumber +
                "\nCustomer Name: " + customerName +
                "\nMortgage Amount: $" + mortgageAmount +
                "\nInterest Rate: " + (interestRate * 100) + "%" +
                "\nTerm: " + term + " year(s)" +
                "\nTotal Owed: $" + calculateTotalAmountOwed();
    }
}