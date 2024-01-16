package app.hotelbooking.system;

import java.time.LocalDate;

public class Invoice {
    private LocalDate invoiceDate;
    User customer;
    private boolean isPaid;
    private String paidWith;
    private String ccNo;
    private InvoiceLineAbstract[] invoiceLines;
    private int totalLine;


    public Invoice(LocalDate invoiceDate, User customer) {
        this.invoiceDate = invoiceDate;
        this.customer = customer;
        this.isPaid = false;
        this.invoiceLines = new InvoiceLineAbstract[8];
        this.totalLine = 0;
    }


    public void payByCash() {
        this.setPaid(true);
        this.paidWith = "cash";
    }

    public boolean payByCard(String ccNo) {
//        int checksum = Character.getNumericValue(ccNo.charAt(ccNo.length() - 1));
//        int total = 0;
//
//        for (int i = ccNo.length() - 2; i >= 0; i--) {
//            int sum = 0;
//            int digit = Character.getNumericValue(ccNo.charAt(i));
//            if (i % 2 == 0) {
//                digit *= 2;
//            }
//
//            sum = digit / 10 + digit % 10;
//            total += sum;
//        }
//
//        boolean validCard =  10 - total % 10 == checksum;
//
//        if (!validCard)
//            return false;
//
       this.isPaid = true;
        this.ccNo = ccNo;
        this.paidWith = "card";

        return true;
    }


    public double getTotalPrice() {
        double sum = 0.;

        for (int i = 0; i < this.totalLine; i++) {
            InvoiceLineAbstract line = this.invoiceLines[i];
            sum += line.getPrice() * line.getQuantity();
        }
        return sum;
    }


    /**
     * 
     *  receipt be looking like this
     * 
     *  Receipt
     *  =======
     *  Customer: %s
     *  Date: %s
     *
     *  Items
     *  -----
     *  %d. %-20s x%d RM %6.2f
     * 
     *  1. Room 1 0 3           x2 RM  12.00
     *                                          
     *  Total                    = RM %6.2f
     *  Paid with: %s
     *  (if card)Card No.: %s
     * 
     */
    public String getReceipt() {

        if (!this.isPaid) {
            return "This invoice has not been paid yet.";
        }

        StringBuilder receipt = new StringBuilder();

        receipt.append("Receipt\n");
        receipt.append("=======\n");
        receipt.append("Customer: " + this.customer.getName() + "\n");
        receipt.append("Date: " + this.invoiceDate.toString() + "\n");
        receipt.append("\nItems\n-----\n");
        
        for (int i = 0; i < this.totalLine;) {
            InvoiceLineAbstract invoiceLine = this.invoiceLines[i++];

            receipt.append(invoiceLine.getLineString(i) + "\n\n");
        }

        receipt.append(
            String.format(
                "Total                         = RM %6.2f\n", 
                this.getTotalPrice()));
        
        receipt.append("Paid with: " + this.paidWith + "\n");
        if (this.paidWith.equals("card")) {
            int cardLength = ccNo.length();

            String cardInReceipt = "";
            for (int i = 0; i < cardLength - 4; i++) {
                cardInReceipt += "X";
            }
            cardInReceipt +=ccNo.substring(ccNo.length() - 4);

            receipt.append("Card: " + cardInReceipt + "\n");
        }

        return receipt.toString();
        

        // if (paidWith.equals("cash")) {
        //     String format = "RECEIPT\n" +
        //             "=======\n" +
        //             "Date: %s\n" +
        //             "\n" +
        //             "Room: %s\n" +
        //             "Room Price: RM%.2f\n" +
        //             "\n" +
        //             "Booking:\n" +
        //             "  Booker Name: %s\n" +
        //             "  Start Date : %s\n" +
        //             "  Last Date  : %s\n" +
        //             "  Duration   : %s\n" +
        //             "\n" +
        //             "Total Price: RM%.2f\n" +
        //             "\n" +
        //             "Paid with: %s";

        //     return String.format(format, 
        //         this.invoiceDate,
        //         this.theRoomToBeBooked,
        //         this.theRoomToBeBooked.getPrice(),
        //         this.customer.getName(),
        //         this.startBookingDate,
        //         this.startBookingDate.plusDays(this.bookingDuration.toDays()-1),
        //         this.bookingDuration.toDays(),
        //         this.theRoomToBeBooked.getPrice() * this.bookingDuration.toDays(),
        //         "Cash"
        //     );
        // }
        // else if (this.paidWith.equals("card")) {
        //     String format = "RECEIPT\n" +
        //             "=======\n" +
        //             "Date: %s\n" +
        //             "\n" +
        //             "Room: %s\n" +
        //             "Room Price: RM%.2f\n" +
        //             "\n" +
        //             "Booking:\n" +
        //             "  Booker Name: %s\n" +
        //             "  Start Date : %s\n" +
        //             "  Last Date  : %s\n" +
        //             "  Duration   : %s\n" +
        //             "\n" +
        //             "Total Price: RM%.2f\n" +
        //             "\n" +
        //             "Paid with: %s\n" +
        //             "Card: %s";

        //     int cardLength = ccNo.length();

        //     String cardInReceipt = "";
        //     for (int i = 0; i < cardLength - 4; i++) {
        //         cardInReceipt += "X";
        //     }
        //     cardInReceipt +=ccNo.substring(ccNo.length() - 4);

        //     return String.format(format, 
        //         this.invoiceDate,
        //         this.theRoomToBeBooked,
        //         this.theRoomToBeBooked.getPrice(),
        //         this.customer.getName(),
        //         this.startBookingDate,
        //         this.startBookingDate.plusDays(this.bookingDuration.toDays()),
        //         this.bookingDuration.toDays(),
        //         this.theRoomToBeBooked.getPrice() * this.bookingDuration.toDays(),
        //         "Card", cardInReceipt);
        // }
        // return "This invoice is being paid by unknown type of payment method.";
    }
    

    /**
     * https://dba.stackexchange.com/questions/102903/is-it-acceptable-to-have-circular-foreign-key-references-how-to-avoid-them
     * @param invoiceLine
     * @return
     */
    public boolean addInvoiceLine(InvoiceLineAbstract invoiceLine) {
        if (this.totalLine == this.invoiceLines.length) {
            return false;
        }

        this.invoiceLines[this.totalLine++] = invoiceLine;
        invoiceLine.setInvoice(this);

        return true;
    }

    public InvoiceLineAbstract[] getInvoiceLines() {
        return invoiceLines;
    }


    public void setInvoiceLines(InvoiceLineAbstract[] invoiceLines) {
        this.invoiceLines = invoiceLines;
    }


    public int getTotalLine() {
        return totalLine;
    }


    public void setTotalLine(int totalLine) {
        this.totalLine = totalLine;
    }


    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }


    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }


    public User getCustomer() {
        return customer;
    }


    public void setCustomer(User customer) {
        this.customer = customer;
    }


    public boolean isPaid() {
        return isPaid;
    }


    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }


    public String getPaidWith() {
        return paidWith;
    }


    public void setPaidWith(String paidWith) {
        this.paidWith = paidWith;
    }


    public String getCcNo() {
        return ccNo;
    }


    public void setCcNo(String ccNo) {
        this.ccNo = ccNo;
    }
    

}
