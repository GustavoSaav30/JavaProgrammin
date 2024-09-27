package exercise3;

public class User {
    String  name;

    public void setName(String firstName) {
        this.name = firstName;
    }
    public void setName(String firstName, String lastName) {
        this.name = String.format("%s %s", firstName, lastName);
    }
    public void setName(String firstName, String lastName, String middleName) {
        this.name = String.format("%s %s %s", firstName, middleName, lastName);
    }

    public String getName() {
        return name;
    }
}
