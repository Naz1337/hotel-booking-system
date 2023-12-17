package app.hotelbooking.system;

import java.time.Duration;
import java.time.LocalDate;

public class Invoice {
    private LocalDate invoiceDate;
    private Room theRoomToBeBooked;
    private LocalDate startBookingDate;
    private Duration bookingDuration;
    private boolean isPaid;


    public Invoice(LocalDate invoiceDate, Room theRoomToBeBooked, LocalDate startBookingDate,
            Duration bookingDuration) {
        this.invoiceDate = invoiceDate;
        this.theRoomToBeBooked = theRoomToBeBooked;
        this.startBookingDate = startBookingDate;
        this.bookingDuration = bookingDuration;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }



    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }



    public Room getTheRoomToBeBooked() {
        return theRoomToBeBooked;
    }



    public void setTheRoomToBeBooked(Room theRoomToBeBooked) {
        this.theRoomToBeBooked = theRoomToBeBooked;
    }



    public LocalDate getStartBookingDate() {
        return startBookingDate;
    }



    public void setStartBookingDate(LocalDate startBookingDate) {
        this.startBookingDate = startBookingDate;
    }



    public Duration getBookingDuration() {
        return bookingDuration;
    }



    public void setBookingDuration(Duration bookingDuration) {
        this.bookingDuration = bookingDuration;
    }



    public double getTotalPrice() {
        return this.getBookingDuration().toDays() * this.getTheRoomToBeBooked().getPrice();
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public void payByCash() {
        this.setPaid(true);
    }
}
