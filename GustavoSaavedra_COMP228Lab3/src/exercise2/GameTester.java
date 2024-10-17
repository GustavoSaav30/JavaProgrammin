package exercise2;

abstract class GameTester {
    protected String name;
    protected boolean isFullTime;

    public GameTester(String name, boolean isFullTime) {
        this.name = name;
        this.isFullTime = isFullTime;
    }

    public abstract double calculateSalary();

    public void displayInfo() {
        System.out.println("Name: " + name);
        if (isFullTime){
            System.out.println("Employment is Full time");
        } else {
            System.out.println("Employment is Part time");
        }
    }
}