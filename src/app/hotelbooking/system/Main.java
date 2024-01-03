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
    
    /**
     * Justification: https://stackoverflow.com/questions/14142853/close-a-scanner-linked-to-system-in
     */
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // LocalDate hehe = LocalDate.of(2023, 12, 7);
        // System.out.println(hehe.plusDays(3));

        // String ccNumber = "4628886137799272";

        // System.out.println(isValidLuhn(ccNumber));

        Hotel hotelUMP = Hotel.getInstance().setHotelName("UMP Hotel");

        hotelUMP.addCustomer(new User(1, "Alice Smith", "1234567890"));
        hotelUMP.addCustomer(new User(2, "Bob Johnson", "2345678901"));
        hotelUMP.addCustomer(new User(3, "Charlie Brown", "3456789012"));
        hotelUMP.addCustomer(new User(4, "Diana Prince", "4567890123"));
        hotelUMP.addCustomer(new User(5, "Edward King", "5678901234"));
        hotelUMP.addCustomer(new User(6, "Fiona Queen", "6789012345"));

        Booking testBook = null;
        testBook = (Booking)hotelUMP.provideService(
            hotelUMP.getCustomerByID(6).get(), 
            hotelUMP.getRooms()[3], 
            LocalDate.of(2023, 12, 20), 
            Duration.ofDays(1));
        testBook.getInvoice().payByCash();

        testBook = (Booking)hotelUMP.provideService(
            hotelUMP.getCustomerByID(5).get(), 
            hotelUMP.getRooms()[1], 
            LocalDate.of(2023, 12, 20), 
            Duration.ofDays(1));
        testBook.getInvoice().payByCash();
        
        testBook = (Booking)hotelUMP.provideService(
            hotelUMP.getCustomerByID(4).get(), 
            hotelUMP.getRooms()[7], 
            LocalDate.of(2023, 12, 20), 
            Duration.ofDays(1));
        testBook.getInvoice().payByCash();
        
        testBook = (Booking)hotelUMP.provideService(
            hotelUMP.getCustomerByID(3).get(), 
            hotelUMP.getRooms()[1], 
            LocalDate.of(2023, 12, 25), 
            Duration.ofDays(1));
        testBook.getInvoice().payByCash();
        
        testBook = (Booking)hotelUMP.provideService(
            hotelUMP.getCustomerByID(6).get(), 
            hotelUMP.getRooms()[0], 
            LocalDate.of(2023, 12, 25), 
            Duration.ofDays(3));
        testBook.getInvoice().payByCash();
        
        /**
         * Summary of the test
         * The room 1-4, 1-2, 2-4 is booked from *2pm* 20231220 until 12pm 20231221
         * The room 1-2 is booked from *2pm* 20231225 until 12pm 20231226
         * The room 1-1 is booked from *2pm* 20231225 until 12pm 20231228
         * 
         * if checking in at 2023-12-19 and checking out in 2023-12-20, room 1-4, 1-2, 2-4 should be available.
         * if checking in at 2023-12-20 and checking out in 2023-12-22, room 1-4, 1-2, 2-4 should be unavailable.
         * if checking in at 2023-12-19 and checking out in 2023-12-27, room 1-1, 1-2, 1-4, 2-4 should be unavailable.
         */

        while (true) {
            crossPlatformClearScreen();
            System.out.println("Welcome to UMP Hotel Booking System Interface!\n");

            for (int i = 0; i < hotelUMP.numberOfCustomers; i++) {
                User customer = hotelUMP.getCustomers()[i];

                System.out.println(String.format(
                    "%d. %s", customer.getUserID(), customer.getName()));
            }

            System.out.println("\nPlease choose the number of the customer to login as or type 'register' to register a new customer " +
                               "or 'quit' to quit!");
            System.out.print("INPUT: ");
            String userInput1 = scanner.nextLine().toLowerCase();

            userInput1 = userInput1.toLowerCase();

            if (userInput1.startsWith("register")) {
                System.out.println("This flow is not implemented yet. please check back later");
                pressEnterToContinue();
                continue;
            } else if (userInput1.equals("quit")) {
                break;
            }

            Optional<User> searchResult = hotelUMP.getCustomerByID(Integer.valueOf(userInput1));

            if (! searchResult.isPresent()) {
                System.out.println("The ID you typed in is invalid. please try again later");
                pressEnterToContinue();
                continue;
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

            if (startingDate.equals(lastDate)) {
                System.out.println("You can not check out the same date you are checking in! Please try again later...");
                pressEnterToContinue();
                continue;
            }

            // TODO: check kalau last date tu sebelum start date dia hahaha
            
            Room[] availableRooms = hotelUMP.avaliableRooms(
                startingDate, 
                lastDate);

            Duration stayDuration = Duration.between(startingDate.atStartOfDay(), lastDate.atStartOfDay());
            crossPlatformClearScreen();


            System.out.println("From your inputted booking dates, we have determine that the following rooms are avaialable for you to book.");
            System.out.println(String.format("The duration of your stay: %d\n", stayDuration.toDays()));
            for (int i = 0; i < availableRooms.length; i++) {
                Room room = availableRooms[i];
                String format = "%d Room: %s Bed: %d RM%.2f";
                double price = room.getPrice() * stayDuration.toDays();
                System.out.println(String.format(format, i + 1, room, room.getBedCount(), price));
                // System.out.println(String.format("%d. %s", i + 1, room));
            }

            System.out.println("\nWrite the number of the room that you would like to book.");
            System.out.print("INPUT: ");

            int userInput4 = Integer.valueOf(scanner.nextLine()) - 1;

            // Optional<Room> roomGetResult = hotelUMP.getRoomByName(userInput4);
            // if (! roomGetResult.isPresent()) {
            //     System.out.println("Sorry, the inputted room name does not exist. Please try again later.");
            //     pressEnterToContinue();
            //     continue;
            // }

            // Room roomToBeBooked = roomGetResult.get();

            Room roomToBeBooked = availableRooms[userInput4];

            Booking booking = (Booking)hotelUMP.provideService(chosenOne, roomToBeBooked, startingDate, stayDuration);
            Invoice bookingInvoice = booking.getInvoice();

            crossPlatformClearScreen();
            System.out.println(String.format("You have choosen to book the room %s for %d day(s).", roomToBeBooked, stayDuration.toDays()));
            System.out.println("Now we need you to choose your payment method to pay for the booking");
            System.out.println("\n1. Cash\n2. Card\n");
            System.out.print("INPUT: ");

            boolean paymentSucess = true;

            int userInputPaymentMethod = Integer.valueOf(scanner.nextLine().toLowerCase());


            if (userInputPaymentMethod == 1) {
                bookingInvoice.payByCash();
            } else if (userInputPaymentMethod == 2) {
                System.out.println("\n You have choosen to pay with Card.");
                System.out.println("Please enter your credit card number.\n");
                System.out.print("INPUT: ");
                String ccNo = scanner.nextLine();
                if (!bookingInvoice.payByCard(ccNo)) {
                    System.out.println("\nThe card you just entered is invalid. Please try again later");
                    paymentSucess = false;
                }
            }
            
            if (!paymentSucess) {
                pressEnterToContinue();
                continue;
            }

            crossPlatformClearScreen();

            System.out.println(bookingInvoice.getReceipt());
            System.out.println("\n The payment succeed! Above is the receipt for your booking. Thank you for choosing UMP Hotel.");
            pressEnterToContinue();
        }
        


        scanner.close();
    }

    public static void crossPlatformClearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.print('\n');
        }
    }

    public static void pressEnterToContinue() {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }
}
