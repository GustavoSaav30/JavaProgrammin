package exercise3;

public class User {

    public static String screamName(String firstName) {
        return firstName;
    }

    public static String screamName(String firstName, String lastName) {
        return String.format("%s %s", firstName, lastName).toUpperCase();
    }

    public static String screamName(String firstName, String middleName, String lastName) {
        return String.format("%s %s %s", firstName, middleName, lastName).toUpperCase();
    }

}
