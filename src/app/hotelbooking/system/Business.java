package app.hotelbooking.system;

import java.util.Optional;

/**
 * Business
 */
public abstract class Business {
    protected User[] customers;
    protected int numberOfCustomers;

    public Business() {
        this.customers = new User[128];
        numberOfCustomers = 0;
    }

    public boolean addCustomer(User user) {
        if (this.numberOfCustomers == this.customers.length) 
            return false;
        
        this.customers[this.numberOfCustomers++] = user;
        return true;
    }

    public User[] getCustomers() {
        return customers;
    }



    public void setCustomers(User[] users) {
        this.customers = users;
    }

    public Optional<User> getCustomerByID(int ID) {
        User users[] = this.getCustomers();
        for (int i = 0; i < this.numberOfCustomers; i++) {
            User user = users[i];

            if (user.getUserID() == ID)
                return Optional.of(user);
        }

        return Optional.empty();
    }

    abstract public Service provideService(String serviceType, User customer, Object... informations);
}