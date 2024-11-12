package exercise2;

//abstract class that will be parent class for fulltime and partime game testers
abstract class GameTester {
    protected String name;
    protected boolean isFullTime; //is full time?

    //constructor that will assign the attributes name and isFullTime
    public GameTester(String name, boolean isFullTime) {
        this.name = name;
        this.isFullTime = isFullTime;
    }

    //abstract method that will be implement in the childrens class
    public abstract double calculateSalary();

    //method that will be overriden in the childrens class to add more information
    public void displayInfo() {
        System.out.println("Name: " + name);
        if (isFullTime){
            System.out.println("Employment is Full time");
        } else {
            System.out.println("Employment is Part time");
        }
    }
}