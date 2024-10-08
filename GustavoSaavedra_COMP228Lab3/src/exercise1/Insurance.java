package exercise1;

public abstract class Insurance {
    private String type;
    private double monthlyCost;

    public Insurance(String insuranceType) {
        this.type = insuranceType;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }

    public String getType() {
        return type;
    }

    public abstract void setInsuranceCost();
    public abstract void displayInfo();
}
