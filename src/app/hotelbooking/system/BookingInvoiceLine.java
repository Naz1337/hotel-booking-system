package app.hotelbooking.system;

import java.time.Duration;
import java.time.LocalDate;

public class BookingInvoiceLine {
    private LocalDate invoiceDate;
    private Room theRoomToBeBooked;
    private LocalDate startBookingDate;
    private Duration bookingDuration;
    private boolean isPaid;
    private String paidWith;
    private String ccNo;
    private User customer;

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

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
                    "  Booker Name: %s\n" +
                    "  Start Date : %s\n" +
                    "  Last Date  : %s\n" +
                    "  Duration   : %s\n" +
                    "\n" +
                    "Total Price: RM%.2f\n" +
                    "\n" +
                    "Paid with: %s";

            return String.format(format, 
                this.invoiceDate,
                this.theRoomToBeBooked,
                this.theRoomToBeBooked.getPrice(),
                this.customer.getName(),
                this.startBookingDate,
                this.startBookingDate.plusDays(this.bookingDuration.toDays()-1),
                this.bookingDuration.toDays(),
                this.theRoomToBeBooked.getPrice() * this.bookingDuration.toDays(),
                "Cash"
            );
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
                    "  Booker Name: %s\n" +
                    "  Start Date : %s\n" +
                    "  Last Date  : %s\n" +
                    "  Duration   : %s\n" +
                    "\n" +
                    "Total Price: RM%.2f\n" +
                    "\n" +
                    "Paid with: %s\n" +
                    "Card: %s";

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
                this.customer.getName(),
                this.startBookingDate,
                this.startBookingDate.plusDays(this.bookingDuration.toDays()),
                this.bookingDuration.toDays(),
                this.theRoomToBeBooked.getPrice() * this.bookingDuration.toDays(),
                "Card", cardInReceipt);
        }
        return "This invoice is being paid by unknown type of payment method.";
    }

    public String getPaidWith() {
        return paidWith;
    }

    public void setPaidWith(String paidWith) {
        this.paidWith = paidWith;
    }

    public BookingInvoiceLine(LocalDate invoiceDate, Room theRoomToBeBooked, LocalDate startBookingDate,
            Duration bookingDuration, User customer) {
        this.invoiceDate = invoiceDate;
        this.theRoomToBeBooked = theRoomToBeBooked;
        this.startBookingDate = startBookingDate;
        this.bookingDuration = bookingDuration;
        this.customer = customer;
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

    public boolean payByCard(String ccNo) {
        int checksum = Character.getNumericValue(ccNo.charAt(ccNo.length() - 1));
        int total = 0;

        for (int i = ccNo.length() - 2; i >= 0; i--) {
            int sum = 0;
            int digit = Character.getNumericValue(ccNo.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
            }

            sum = digit / 10 + digit % 10;
            total += sum;
        }

        boolean validCard =  10 - total % 10 == checksum;

        if (!validCard)
            return false;

        this.isPaid = true;
        this.ccNo = ccNo;
        this.paidWith = "card";

        return true;
    }

    public String getCcNo() {
        return ccNo;
    }

    public void setCcNo(String ccNo) {
        this.ccNo = ccNo;
    }
}
