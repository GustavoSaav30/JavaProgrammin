package exercise1;

public class Life extends Insurance {

    public Life() {
        super("Life");
    }

    @Override
    public void setInsuranceCost(double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    @Override
    public void displayInfo() {
        System.out.println(getType());
        System.out.println(getMonthlyCost());
    }
}
