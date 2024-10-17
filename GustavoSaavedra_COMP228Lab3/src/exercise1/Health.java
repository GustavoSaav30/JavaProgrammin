package exercise1;

public class Health extends Insurance {

    public Health() {
        super("Health");
    }

    @Override
    public void setInsuranceCost(double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    @Override
    public void displayInfo() {
        System.out.println("Insurance type: " + getType());
        System.out.println("Monthly bill: $" + getMonthlyCost());
    }
}