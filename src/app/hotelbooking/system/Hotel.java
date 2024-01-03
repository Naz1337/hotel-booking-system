package app.hotelbooking.system;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

public class Hotel extends Business {
    private String hotelName;
    private Room[] rooms;
    private Booking[] bookings;
    private int numberOfBookings;
    private static final Hotel INSTANCE = new Hotel();

    
    public Hotel() {
        super();
        this.hotelName = null;
        this.rooms = new Room[8];
        this.bookings = new Booking[768];
        this.numberOfBookings = 0;

        this.rooms[0] = new Room(1, 1, 50., 1, 1);
        this.rooms[1] = new Room(2, 1, 50., 1, 2);
        this.rooms[2] = new Room(3, 1, 50., 1, 3);
        this.rooms[3] = new Room(4, 1, 50., 1, 4);
        this.rooms[4] = new Room(5, 4, 300., 2, 1);
        this.rooms[5] = new Room(6, 4, 300., 2, 2);
        this.rooms[6] = new Room(7, 4, 300., 2, 3);
        this.rooms[7] = new Room(8, 4, 300., 2, 4);




    }

    public Optional<Room> getRoomByName(String roomName) {
        for (int i = 0; i < this.rooms.length; i++) {
            Room room = this.rooms[i];

            if (room.getRoomName().equals(roomName)) {
                return Optional.of(room);
            }
        }

        return Optional.empty();
    }

    public static Hotel getInstance() {
        return Hotel.INSTANCE;
    }

    public boolean addBooking(Booking book) {
        if (this.bookings.length == this.numberOfBookings)
            return false;
        
        this.bookings[this.numberOfBookings++] = book;
        return true;
    }

    /**
     * nanti guna bookings untuk tengok available ke tak
     * @param room
     * @return
     */
    public Room[] avaliableRooms(LocalDate startingDate, LocalDate lastDate) {
        Room[] notList = new Room[4];
        int notListLength = 0;
        
        for (int i = 0; i < this.rooms.length; i++) {
            Room room = this.rooms[i];

            // cari booking yang sama dengan bilik ni
            Booking[] bookings = this.findBookingRelatedTo(room);

            boolean available = true;
            for (int j = 0; j < bookings.length; j++) {
                Booking book = bookings[j];

                if (!book.isBookingVerified()) {
                    continue;
                }

                LocalDate lastBook = book.getStartDate().plusDays(book.getBookingDuration().toDays());

                if (
                    !(
                        startingDate.isAfter(lastBook.plusDays(-1)) 
                        || ((!(startingDate.isAfter(book.getStartDate())))
                        && book.getStartDate().isAfter(lastDate.plusDays(-1)))
                    )
                ) {
                    available = false;
                    break;
                }
            }

            if (available) {
                notList[notListLength++] = room;

                if ((double)notListLength / (double)notList.length > 0.75) {
                    Room[] temp = new Room[notList.length*2];
                    
                    for (int j = 0; j < notListLength; j++) {
                        temp[j] = notList[j];
                    }

                    notList = temp;
                }
            }

        }

        Room[] finalRooms = new Room[notListLength];
        for (int i = 0; i < finalRooms.length; i++) {
            finalRooms[i] = notList[i];
        }

        return finalRooms;
    }

    public Booking[] findBookingRelatedTo(Room room) {
        Booking[] notList = new Booking[4];
        int numberOfBookingInNotList = 0;

        for (int i = 0; i < this.numberOfBookings; i++) {
            Booking book = this.bookings[i];

            if (book.getBookedRoom() != room) {
                continue;
            }

            notList[numberOfBookingInNotList++] = book;
            if ((double)numberOfBookingInNotList / (double)notList.length < 0.75) {
                continue;
            }

            Booking[] temp = new Booking[notList.length*2];
            for (int j = 0; j < numberOfBookingInNotList; j++) {
                temp[j] = notList[j];
            }
            notList = temp;  // GC ⭐
        }

        Booking[] shrinkedArray = new Booking[numberOfBookingInNotList];

        for (int i = 0; i < shrinkedArray.length; i++) {
            shrinkedArray[i] = notList[i];
        }
        return shrinkedArray;
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
 * @param serviceType can be either "booking" or "lunch"
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
    public Service provideService(String serviceType, User customer, Object... informations) {
        if (serviceType.equals("booking")) {
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

            Booking booking = new Booking(bookedRoom, customer, startDate, bookingDuration);

            if (! this.addBooking(booking)) {
                System.out.println("[WARN] Bookings array is full 👍");
                return null;
            }
            return (Service)(booking);
        }
        else if (serviceType.equals("lunch")) {
            if (!(informations[0] instanceof Integer)) {
                return null;
            }
            int paxNo = (Integer)informations[0];

            Lunch lunch = new Lunch(paxNo);
            return (Service) lunch;
        }
        
        
        return null;
    }
}
