package app.hotelbooking.system;

import java.time.LocalDate;
import java.time.Duration;

public interface Bookable {
    public Booking book(User theBooker, LocalDate starDate, Duration bookingDuration);
}
