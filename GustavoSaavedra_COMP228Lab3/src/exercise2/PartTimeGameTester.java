package exercise2;

class PartTimeGameTester extends GameTester {
    private int hoursWorked;

    public PartTimeGameTester(String name, int hoursWorked) {
        super(name, false);
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * 20.0;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Hourly pay rate: $20.0");
        System.out.println("Hours: " + hoursWorked);
        System.out.println("Final Salary: $" + calculateSalary());
    }
}

