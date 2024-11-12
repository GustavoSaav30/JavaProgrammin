package exercise1;

//class health that extends Insurance parent class
public class Health extends Insurance {

    //Health class constructor that assigns the Insurance type by refering the parent class
    //constructor using the super
    public Health() {
        super("Health");
    }

    //implementing the setInsuranceCost by overrinding it
    @Override
    public void setInsuranceCost(double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    //implementing the displayInfo method by overrinding it
    @Override
    public void displayInfo() {
        System.out.println("Insurance type: " + getType());
        System.out.println("Monthly bill: $" + getMonthlyCost());
    }
}