package app.hotelbooking.system;

import java.time.LocalDate;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
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

        hotelUMP.provideService(
            hotelUMP.getCustomerByID(6).get(), 
            hotelUMP.getRooms()[3], 
            LocalDate.of(2023, 12, 20), 
            Duration.ofDays(20));

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

        crossPlatformClearScreen();

        System.out.println("Before you start, we need to know when your booking start and end.\n");
        System.out.print("For the starting date of your booking, please enter the date in the format (DD/MM/YYYY): ");
        String userInput2 = scanner.nextLine();

        System.out.print("Now, enter the last day of your booking in the same format (DD/MM/YYYY): ");
        String userInput3 = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate startingDate = LocalDate.parse(userInput2, formatter);
        LocalDate lastDate = LocalDate.parse(userInput3, formatter);

        // TODO: buat kalau invalid input/date, mintak balik

        // test

        
        
        Room[] availableRooms = hotelUMP.avaliableRooms(
            startingDate, 
            lastDate);

        Duration stayDuration = Duration.between(startingDate.atStartOfDay(), lastDate.plusDays(1).atStartOfDay());
        crossPlatformClearScreen();


        System.out.println("From your inputted booking dates, we have determine that the following rooms are avaialable for you to book.");
        System.out.println(String.format("The duration of your stay: %d\n", stayDuration.toDays()));
        for (int i = 0; i < availableRooms.length; i++) {
            Room room = availableRooms[i];
            String format = "Room: %s Bed: %d RM%.2f";
            double price = room.getPrice() * stayDuration.toDays();
            System.out.println(String.format(format, room, room.getBedCount(), price));
            // System.out.println(String.format("%d. %s", i + 1, room));
        }

        System.out.println("\nWrite the name of the room that you would like to book.");
        System.out.print("INPUT: ");
        String userInput4 = scanner.nextLine().strip();

        Optional<Room> roomGetResult = hotelUMP.getRoomByName(userInput4);
        if (! roomGetResult.isPresent()) {
            System.out.println("Sorry, the inputted room name does not exist. Please try again later.");
            return;
        }

        Room roomToBeBooked = roomGetResult.get();

        Booking booking = (Booking)hotelUMP.provideService(chosenOne, roomToBeBooked, startingDate, stayDuration);
        Invoice bookingInvoice = booking.getInvoice();

        crossPlatformClearScreen();
        System.out.println(String.format("You have choosen to book the room %s for %d day(s).", roomToBeBooked, stayDuration.toDays()));
        System.out.println("Now we need you to choose your payment method to pay for the booking");
        System.out.println("\n1. Cash\n2. Card\n");
        System.out.print("INPUT: ");

        boolean paymentSucess = true;
        String userInputPaymentMethod = scanner.nextLine().toLowerCase();
        if (userInputPaymentMethod.equals("cash")) {
            bookingInvoice.payByCash();
        } else if (userInputPaymentMethod.equals("card")) {
            System.out.println("\n You have choosen to pay with Card.");
            System.out.println("Please enter your credit card number.\n");
            System.out.print("INPUT: ");
            String ccNo = scanner.nextLine().strip();
            if (!bookingInvoice.payByCard(ccNo)) {
                System.out.println("\nThe card you just entered is invalid. Please try again later");
                paymentSucess = false;
            }
        }
        
        if (!paymentSucess) return;

        crossPlatformClearScreen();

        System.out.println(bookingInvoice.getReceipt());
        System.out.println("\n The payment succeed! Above is the receipt for your booking. Thank you for choosing UMP Hotel.");



        scanner.close();
    }

    public static void crossPlatformClearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.print('\n');
        }
    }
}
