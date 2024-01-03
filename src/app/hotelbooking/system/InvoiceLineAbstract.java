package app.hotelbooking.system;

public abstract class InvoiceLineAbstract {
    protected String lineTitle;
    protected int quantity;
    protected double price;
    protected Invoice invoice;
    
    public InvoiceLineAbstract(String lineTitle, int quantity, double price) {
        this.lineTitle = lineTitle;
        this.quantity = quantity;
        this.price = price;
    }

    public String getLineString(int number) {
        return String.format("%d. %-25s x%d RM %6.2f", number, this.lineTitle, this.quantity, this.price * this.quantity);
    }

    public String getLineTitle() {
        return lineTitle;
    }

    public void setLineTitle(String lineTitle) {
        this.lineTitle = lineTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
