package exercise3;

public class Main {
    public static void main(String[] args) {
        // Screams the first name
        String chat1 = Chat.screamName("Gustavo");
        System.out.println(chat1);
        // Screams the first name and Last Name
        String chat2 = Chat.screamName("Gustavo", "Sanchez");
        System.out.println(chat2);
        // Screams the first name, middle name and Last Name
        String chat3 = Chat.screamName("Gustavo", "Saavedra", "Sanchez");
        System.out.println(chat3);
    }
}
