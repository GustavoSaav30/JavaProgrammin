package exercise2;

//children class of GameTester class
class FullTimeGameTester extends GameTester {

    //constructor that passes isFullTime true and name of the game tester
    public FullTimeGameTester(String name) {
        super(name, true);
    }
    //overriding calculateSalary to a fixed salary
    @Override
    public double calculateSalary() {
        return 3000.00;
    }
    //overriding the displayInfo to display the info from the super class and adding
    //one more line showing the salary
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Salary: $" + calculateSalary());
    }
}