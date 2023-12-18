package app.hotelbooking.system;

import java.time.Duration;
import java.time.LocalDate;

public class Invoice {
    private LocalDate invoiceDate;
    private Room theRoomToBeBooked;
    private LocalDate startBookingDate;
    private Duration bookingDuration;
    private boolean isPaid;
    private String paidWith;
    private String ccNo;

    public String getReceipt() {

        if (!this.isPaid) {
            return "This invoice has not been paid yet.";
        }
        

        if (paidWith.equals("cash")) {
            String format = "RECEIPT\n" +
                    "=======\n" +
                    "Date: %s\n" +
                    "\n" +
                    "Room: %s\n" +
                    "Room Price: RM%.2f\n" +
                    "\n" +
                    "Booking:\n" +
                    "  Start Date: %s\n" +
                    "  Last Date : %s\n" +
                    "  Duration  : %s\n" +
                    "\n" +
                    "Total Price: RM%.2f\n" +
                    "\n" +
                    "Paid with: %s\n";

            return String.format(format, 
                this.invoiceDate,
                this.theRoomToBeBooked,
                this.theRoomToBeBooked.getPrice(),
                this.startBookingDate,
                this.startBookingDate.plusDays(this.bookingDuration.toDays()-1),
                this.bookingDuration.toDays(),
                this.theRoomToBeBooked.getPrice() * this.bookingDuration.toDays(),
                "Cash"
            ).strip();
        }
        else if (this.paidWith.equals("card")) {
            String format = "RECEIPT\n" +
            "=======\n" +
            "Date: %s\n" +
            "\n" +
            "Room: %s\n" +
            "Room Price: RM%.2f\n" +
            "\n" +
            "Booking:\n" +
            "  Start Date: %s\n" +
            "  Last Date : %s\n" +
            "  Duration  : %s\n" +
            "\n" +
            "Total Price: RM%.2f\n" +
            "\n" +
            "Paid with: %s\n" +
            "Card: %s\n";

            int cardLength = ccNo.length();

            String cardInReceipt = "";
            for (int i = 0; i < cardLength - 4; i++) {
                cardInReceipt += "X";
            }
            cardInReceipt +=ccNo.substring(ccNo.length() - 4);

            return String.format(format, 
                this.invoiceDate,
                this.theRoomToBeBooked,
                this.theRoomToBeBooked.getPrice(),
                this.startBookingDate,
                this.startBookingDate.plusDays(this.bookingDuration.toDays()-1),
                this.bookingDuration.toDays(),
                this.theRoomToBeBooked.getPrice() * this.bookingDuration.toDays(),
                "Card", cardInReceipt).strip();
        }
        return "This invoice is being paid by unknown type of payment method.";
    }

    public String getPaidWith() {
        return paidWith;
    }

    public void setPaidWith(String paidWith) {
        this.paidWith = paidWith;
    }

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
        this.paidWith = "cash";
    }

    public String getCcNo() {
        return ccNo;
    }

    public void setCcNo(String ccNo) {
        this.ccNo = ccNo;
    }
}
