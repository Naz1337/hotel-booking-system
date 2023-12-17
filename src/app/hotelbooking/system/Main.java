package app.hotelbooking.system;

import java.time.LocalDate;
import java.time.Duration;
import java.time.LocalTime;

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

        Hotel hotelUMP = Hotel.getInstance().setHotelName("UMP Hotel");

        
        


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
