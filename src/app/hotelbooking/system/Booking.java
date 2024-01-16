package app.hotelbooking.system;

import java.time.LocalDate;
import java.time.Duration;

public class Booking extends Service{
    private Room bookedRoom;
    private User theBooker;
    private LocalDate startDate;
    private Duration bookingDuration;
    private BookingInvoiceLine invoiceLine;

    public Booking(Room bookedRoom, User theBooker, LocalDate startDate, Duration bookingDuration) {
        super("Hotel Room");
        this.bookedRoom = bookedRoom;
        this.theBooker = theBooker;
        this.startDate = startDate;
        this.bookingDuration = bookingDuration;

        this.invoiceLine = new BookingInvoiceLine(this);
    }

    public Room getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(Room bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public User getTheBooker() {
        return theBooker;
    }

    public void setTheBooker(User theBooker) {
        this.theBooker = theBooker;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Duration getBookingDuration() {
        return bookingDuration;
    }

    public void setBookingDuration(Duration bookingDuration) {
        this.bookingDuration = bookingDuration;
    }

    public BookingInvoiceLine getInvoiceLine() {
        return invoiceLine;
    }

    public void setInvoiceLine(BookingInvoiceLine invoice) {
        this.invoiceLine = invoice;
    }

    public boolean isBookingVerified() {
        Invoice invoice = this.getInvoiceLine().getInvoice();
        if (invoice == null) {
            return false;
        }
        return invoice.isPaid();
    }

}
