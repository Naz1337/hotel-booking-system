package app.hotelbooking.system;

public class User {
    private int userID;
    private String name;
    private String contactNumber;
    
    public User(int userID, String name, String contactNumber) {
        this.userID = userID;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    
}
