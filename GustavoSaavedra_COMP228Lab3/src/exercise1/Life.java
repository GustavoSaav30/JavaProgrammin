package exercise1;

//class Life that extends Insurance parent class
public class Life extends Insurance {

    //Life class constructor that assigns the Insurance type by refering the parent class
    //constructor using the super
    public Life() {
        super("Life");
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