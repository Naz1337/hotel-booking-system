package app.hotelbooking.system;

import java.time.LocalDate;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author Naz
 */
public class Main {

    public static void main(String[] args) {
        // LocalDate hehe = LocalDate.of(2023, 12, 7);
        // System.out.println(hehe.plusDays(3));

        // String ccNumber = "4628886137799272";

        // System.out.println(isValidLuhn(ccNumber));

        Scanner scanner = new Scanner(System.in);

        Hotel hotelUMP = Hotel.getInstance().setHotelName("UMP Hotel");

        hotelUMP.addCustomer(new User(1, "Alice Smith", "1234567890"));
        hotelUMP.addCustomer(new User(2, "Bob Johnson", "2345678901"));
        hotelUMP.addCustomer(new User(3, "Charlie Brown", "3456789012"));
        hotelUMP.addCustomer(new User(4, "Diana Prince", "4567890123"));
        hotelUMP.addCustomer(new User(5, "Edward King", "5678901234"));
        hotelUMP.addCustomer(new User(6, "Fiona Queen", "6789012345"));

        System.out.println("Welcome to UMP Hotel Booking System Interface!\n");

        for (int i = 0; i < hotelUMP.numberOfCustomers; i++) {
            User customer = hotelUMP.getCustomers()[i];

            System.out.println(String.format(
                "%d. %s", customer.getUserID(), customer.getName()));
        }

        System.out.println("\nPlease choose one of the customer to login as or type 'register' to register a new customer");
        System.out.print("INPUT: ");

        String userInput1 = scanner.nextLine();

        userInput1 = userInput1.toLowerCase();

        if (userInput1.startsWith("register")) {
            System.out.println("This flow is not implemented yet. please check back later");
            return;
        }

        Optional<User> searchResult = hotelUMP.getCustomerByID(Integer.valueOf(userInput1));

        if (! searchResult.isPresent()) {
            System.out.println("The ID you typed in is invalid. please tru again later");
            return;
        }
        User chosenOne = searchResult.get();


    }
    
    public static boolean isValidLuhn(String number) {
        int checksum = Character.getNumericValue(number.charAt(number.length() - 1));
        int total = 0;

        for (int i = number.length() - 2; i >= 0; i--) {
            int sum = 0;
            int digit = Character.getNumericValue(number.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
            }

            sum = digit / 10 + digit % 10;
            total += sum;
        }

        return 10 - total % 10 == checksum;
    }
}
