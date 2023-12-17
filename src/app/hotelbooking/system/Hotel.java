package app.hotelbooking.system;

import java.time.Duration;
import java.time.LocalDate;

public class Hotel extends Business {
    private String hotelName;
    private Room[] rooms;
    private Booking[] bookings;
    private static final Hotel INSTANCE = new Hotel();

    
    public Hotel() {
        super();
        this.hotelName = null;
        this.rooms = new Room[128];
        this.bookings = new Booking[768];
    }

    public static Hotel getInstance() {
        return Hotel.INSTANCE;
    }

    /**
     * nanti guna bookings untuk tengok available ke tak
     * @param room
     * @return
     */
    public boolean isRoomAvailable(Room room) {
        return true;
    }


    public String getHotelName() {
        return hotelName;
    }


    public Hotel setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }


    public Room[] getRooms() {
        return rooms;
    }


    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }


    public Booking[] getBookings() {
        return bookings;
    }


    public void setBookings(Booking[] bookings) {
        this.bookings = bookings;
    }

    
    /**
 * Provides a service for a given user based on specific information provided.
 * This method expects exactly three pieces of information in the following order:
 * 1. Room: The room to be booked (instance of Room).
 * 2. LocalDate: The start date of the booking.
 * 3. Duration: The duration of the booking.
 *
 * @param customer The user for whom the service is to be provided.
 * @param informations Varargs providing additional information. This method expects
 *                     exactly three parameters - Room, LocalDate, and Duration, in that order.
 * @return A Booking object, subclass of Service, if the information meets the required criteria;
 *         otherwise, returns null if the information amount is insufficient or the data types do not match the expectation.
 * @see Booking
 * @see Room
 * @see LocalDate
 * @see Duration
 */
    @Override
    public Service provideService(User customer, Object... informations) {
        
        if (informations.length < 3)
            return null;

        if (!(informations[0] instanceof Room)) 
            return null;
        
        Room bookedRoom = (Room)informations[0];

        // User theBooker = customer;

        if (!(informations[1] instanceof LocalDate))
            return null;
        
        LocalDate startDate = (LocalDate)informations[1];

        if (!(informations[2] instanceof Duration))
            return null;
        
        Duration bookingDuration = (Duration)informations[2];

        return (Service)(new Booking(bookedRoom, customer, startDate, bookingDuration));
    }
}
