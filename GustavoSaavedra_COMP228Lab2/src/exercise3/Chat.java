package exercise3;

public class Chat {
    // Returns the uppercase first name with !
    public static String screamName(String firstName) {
        return firstName.toUpperCase() + "!";
    }
    // Returns the uppercase first name and Last name plus !
    public static String screamName(String firstName, String lastName) {
        return String.format("%s %s!", firstName, lastName).toUpperCase();
    }
    // Returns the uppercase first name, middle name and Last name plus !
    public static String screamName(String firstName, String middleName, String lastName) {
        return String.format("%s %s %s!", firstName, middleName, lastName).toUpperCase();
    }

}
