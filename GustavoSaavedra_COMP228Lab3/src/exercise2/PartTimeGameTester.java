package exercise2;
//children class of GameTester class
class PartTimeGameTester extends GameTester {
    private int hoursWorked;
    //constructor that passes isFullTime false and name of the game tester and
    // setting the hoursWorked to calculate the Salary
    public PartTimeGameTester(String name, int hoursWorked) {
        super(name, false);
        this.hoursWorked = hoursWorked;
    }

    //overriding the method to calculateSalary based on the hours worked
    @Override
    public double calculateSalary() {
        return hoursWorked * 20.0;
    }

    //overriding the displayInfo to display the info from the super class and adding
    //a few more lines showing the salary
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Hourly pay rate: $20.0");
        System.out.println("Hours: " + hoursWorked);
        System.out.println("Final Salary: $" + calculateSalary());
    }
}

