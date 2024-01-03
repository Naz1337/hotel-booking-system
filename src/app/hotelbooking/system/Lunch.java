package app.hotelbooking.system;

public class Lunch extends Service {
    private LunchInvoiceLine lunchInvoiceLine;

    public Lunch(int paxNo) {
        super("Lunch");

        this.lunchInvoiceLine = new LunchInvoiceLine(paxNo);
    }

    public LunchInvoiceLine getLunchInvoiceLine() {
        return lunchInvoiceLine;
    }

    public void setLunchInvoiceLine(LunchInvoiceLine lunchInvoiceLine) {
        this.lunchInvoiceLine = lunchInvoiceLine;
    }

    
}
