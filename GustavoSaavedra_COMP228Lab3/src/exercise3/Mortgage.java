package exercise3;

//abstract class that will be parenting Personal and Business Mortgage. It is implementing
//the Constants interface
public abstract class Mortgage implements MortgageConstants {
    private String mortgageNumber;
    private String customerName;
    private double mortgageAmount;
    private double interestRate;
    private int term;

    //mortgage constructor that will have a couple rules for setting the mortgage amount and
    // term
    public Mortgage(String mortgageNumber, String customerName, double mortgageAmount, double interestRate, int term) {
        this.mortgageNumber = mortgageNumber;
        this.customerName = customerName;
        if (mortgageAmount > MAX_MORTGAGE) {
            this.mortgageAmount = MAX_MORTGAGE; // mortgage higher then max
                                                // now goes up to 300000
        } else {
            this.mortgageAmount = mortgageAmount;
        }
        this.interestRate = interestRate;
        // if the term is different from those in the interface, it will force to short term
        if (term != SHORT && term != MEDIUM && term != LONG) {
            this.term = SHORT; // forcing short term
        } else {
            this.term = term;
        }
    }
    // get and setters
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