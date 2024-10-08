package exercise1;

public abstract class Insurance {
    private String type;
    protected double monthlyCost;

    public Insurance(String insuranceType) {
        this.type = insuranceType;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }

    public String getType() {
        return type;
    }

    public abstract void setInsuranceCost(double monthlyCost);
    public abstract void displayInfo();
}
