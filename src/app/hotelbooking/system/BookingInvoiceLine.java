package app.hotelbooking.system;


public class BookingInvoiceLine extends InvoiceLineAbstract {
    private Booking booking;

    public BookingInvoiceLine(Booking booking) {
        super(
            "Room: " + booking.getBookedRoom().getRoomName(), 
            (int)booking.getBookingDuration().toDays(), 
            booking.getBookedRoom().getPrice());
        this.booking = booking;
    }

    @Override
    public String getLineString(int number) {
        StringBuilder theLine = new StringBuilder();

        theLine.append(super.getLineString(number) + "\n");
        theLine.append("     Start Date: " + this.booking.getStartDate().toString() + "\n");
        theLine.append("     End Date: " + this.booking.getStartDate().plusDays(this.booking.getBookingDuration().toDays()) + "\n");
        theLine.append("     Duration: " + String.valueOf(this.booking.getBookingDuration().toDays()) + " day(s)");

        return theLine.toString();
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    
}
