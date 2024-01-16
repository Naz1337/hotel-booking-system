package app.hotelbooking.system;

public class LunchInvoiceLine extends InvoiceLineAbstract {
    private static final double LUNCHPRICE = 14.;

    public LunchInvoiceLine(int paxNo) {
        super("Lunch", paxNo, LUNCHPRICE);
    }
}
