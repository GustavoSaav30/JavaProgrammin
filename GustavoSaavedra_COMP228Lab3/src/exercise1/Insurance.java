package exercise1;

//abstract class Insurance will be parent class for Life and Health
public abstract class Insurance {
    private String type;
    protected double monthlyCost;
    //constructor that assign the insuranceType
    public Insurance(String insuranceType) {
        this.type = insuranceType;
    }
    //getter for MonthlyCost attribute
    public double getMonthlyCost() {
        return monthlyCost;
    }
    //getter for type attribute
    public String getType() {
        return type;
    }
    //abstract methodes that will be implemented by the children classes
    public abstract void setInsuranceCost(double monthlyCost);
    public abstract void displayInfo();
}