package app.hotelbooking.system;

public abstract class Service {
    protected String serviceName;

    public Service(String productName) {
        this.serviceName = productName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    
}
